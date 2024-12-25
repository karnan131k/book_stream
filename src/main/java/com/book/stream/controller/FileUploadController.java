package com.book.stream.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
@Slf4j
public class FileUploadController {

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadEmployeeImage(@RequestParam("folderName") String folderName, @RequestParam("file") MultipartFile file) {

        try {
            String imagePath = saveImage(folderName,file);
            return ResponseEntity.ok(imagePath);
        } catch (Exception e) {
            log.error("Error uploading image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Image Upload Failed");
        }
    }

    @DeleteMapping("/remove-image")
    public ResponseEntity<String> removeEmployeeImage(@RequestParam("folderName") String folderName, @RequestParam("imageName") String imageName) {
        try {
            String message = deleteEmployeeImage(folderName, imageName);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            log.error("Error removing image ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("mage Removal Failed");
        }
    }

    @GetMapping("/images/{folderName}/{imageName}.png")
    public ResponseEntity<?> getEmployeeImage(@PathVariable String folderName, @PathVariable String imageName) {
        // Define the folder where images are stored outside the resources
        String imageFolderPath = "uploads/images" + folderName; // This folder is outside resources

        // Construct the full path to the image
        Path imagePath = Paths.get(imageFolderPath, imageName + ".png");

        // Check if the image file exists
        if (!imagePath.toFile().exists()) {
            return new ResponseEntity<>("Image not found for" + imageName, HttpStatus.NOT_FOUND);
        }

        // Serve the image as a resource
        Resource resource = new FileSystemResource(imagePath);

        // Set headers to disable caching (optional)
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-store, no-cache, must-revalidate, proxy-revalidate");
        headers.setPragma("no-cache");

        // Return the image as part of the response
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    public String saveImage(String folderName, MultipartFile file) throws IOException {

        // Validate file type (only image formats allowed)
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty. Please upload a valid image file.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid file type. Only image formats (e.g., JPEG, PNG) are allowed.");
        }

        // Define the base directory inside the uploads -> images
        String baseDir = new File("uploads" + File.separator + "images" + File.separator + folderName).getAbsolutePath();
        Path imageDirectory = Paths.get(baseDir);

        // Ensure the directory exists
        if (!Files.exists(imageDirectory)) {
            Files.createDirectories(imageDirectory);
        }

        // Generate file name and save
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String imageFileName = getSaltString() + fileExtension;
        Path imagePath = imageDirectory.resolve(imageFileName);

        // Save the image file
        Files.write(imagePath, file.getBytes());

        // Return the image URL
        return "/images/" + folderName + imageFileName;
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            throw new IllegalArgumentException("Invalid file name. Could not determine file extension.");
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public String deleteEmployeeImage(String folderName, String imageName) throws IOException {

        // Define image formats to check
        List<String> imageExtensions = Arrays.asList("png", "jpg", "jpeg", "gif", "bmp");

        // Construct the directory path using platform-independent approach
        String directory = "uploads" + File.separator + "images" + File.separator + "folderName";

        // Check each image format
        for (String ext : imageExtensions) {
            Path filePath = Paths.get(directory + File.separator + imageName + "." + ext);

            // Delete the image file if it exists
            if (Files.deleteIfExists(filePath)) {
                return "Image successfully removed " + imageName + " (" + ext + ")";
            }
        }

        // If no image is found
        return "No image found " + imageName;
    }

}
