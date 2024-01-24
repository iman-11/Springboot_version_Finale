package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class storageservice {

    private final Storagerepo repository;
    private final UserRepository userRepository;

    public String uploadImage(MultipartFile file,String id) throws IOException {
        ImageData  imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();

        imageData=repository.save(imageData);
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

             existingUser.setImageData(imageData);
             userRepository.save(existingUser);
        }

        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }

        return null;

    }

    public byte[] downloadImageByUserId(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Assurez-vous que votre modèle User a une propriété imageData
            ImageData imageData = user.getImageData();

            if (imageData != null) {
                return ImageUtils.decompressImage(imageData.getImageData());
            }
        }

        // Retournez null ou une valeur par défaut si aucune image n'est trouvée
        return null;
    }

}
