package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequestMapping("/image")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class storagecontoller {


    private final storageservice service;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable String id) throws IOException {
        String uploadImage = service.uploadImage(file,id);


        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("imageUrl", uploadImage, "fileName", file.getOriginalFilename()));
    }


    @GetMapping("download/{userId}")
    public ResponseEntity<byte[]> downloadImageByUserId(@PathVariable String userId) {
        byte[] imageData = service.downloadImageByUserId(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);  // ou MediaType.IMAGE_PNG, etc.

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }



}
