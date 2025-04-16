package com.example.Product_APII.Service;

import com.example.Product_APII.Entity.Image;
import com.example.Product_APII.Repository.ImageRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final Path root = Paths.get("uploads");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Không thể tạo thư mục upload");
        }
    }

    public List<Image> uploadImagesGeneric(List<MultipartFile> files, String entityType, Long mappedId) {
        List<Image> savedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = root.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                Image image = Image.builder()
                        .name(fileName)
                        .url("/api/images/download/" + fileName)
                        .type(file.getContentType())
                        .size(file.getSize())
                        .mappedId(mappedId)
                        .entityType(entityType.toLowerCase())
                        .build();

                savedImages.add(imageRepository.save(image));

            } catch (IOException e) {
                throw new RuntimeException("Không thể upload ảnh: " + e.getMessage());
            }
        }

        return savedImages;
    }

    public List<Image> getImagesByEntity(String entityType, Long mappedId) {
        return imageRepository.findByEntityTypeAndMappedId(entityType.toLowerCase(), mappedId);
    }

    public void deleteImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ảnh không tồn tại"));

        Path filePath = root.resolve(image.getName());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Không thể xoá file vật lý: " + e.getMessage());
        }

        imageRepository.delete(image);
    }

    public Resource downloadImage(String filename) {
        try {
            Path filePath = root.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File không tồn tại: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Lỗi tải file: " + e.getMessage());
        }
    }
}
