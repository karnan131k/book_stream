package com.book.stream.auth.repo;

import com.book.stream.auth.entity.ERole;
import com.book.stream.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole eRole);
}
