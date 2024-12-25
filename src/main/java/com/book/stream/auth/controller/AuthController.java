package com.book.stream.auth.controller;

import com.book.stream.auth.dto.request.LoginRequest;
import com.book.stream.auth.dto.request.SignupRequest;
import com.book.stream.auth.dto.request.TokenRefreshRequest;
import com.book.stream.auth.dto.response.JwtResponse;
import com.book.stream.auth.dto.response.TokenRefreshResponse;
import com.book.stream.auth.entity.RefreshToken;
import com.book.stream.auth.entity.User;
import com.book.stream.auth.exception.TokenRefreshException;
import com.book.stream.auth.jwt.JwtUtils;
import com.book.stream.auth.service.AuthService;
import com.book.stream.auth.service.RefreshTokenService;
import com.book.stream.auth.service.impl.UserDetailsImpl;
import com.book.stream.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static com.book.stream.auth.constants.AppConstant.API;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = API)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserDetailsService userDetailsService;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signUp(@Valid @RequestBody SignupRequest signupRequest){
        return new ResponseEntity<>(ApiResponse.success(authService.signUp(signupRequest)), HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<ApiResponse<JwtResponse>> signIn(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(ApiResponse.success(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getUsername(), userDetails.getUsername(), roles)));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ApiResponse<TokenRefreshResponse>> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateToken((UserDetailsImpl) userDetailsService.loadUserByUsername(user.getEmail()));
                    TokenRefreshResponse response = new TokenRefreshResponse(token, requestRefreshToken);
                    return ResponseEntity.ok(ApiResponse.success(response));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}