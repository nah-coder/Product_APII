package com.example.Product_APII.controller;

import com.example.Product_APII.Entity.Image;
import com.example.Product_APII.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    // Upload ảnh cho entity cụ thể
    @PostMapping("/upload/{entityType}/{mappedId}")
    public ResponseEntity<List<Image>> uploadImages(
            @RequestParam("files") List<MultipartFile> files,
            @PathVariable String entityType,
            @PathVariable Long mappedId) {
        return ResponseEntity.ok(imageService.uploadImagesGeneric(files, entityType, mappedId));
    }

    // Lấy danh sách ảnh theo entity
    @GetMapping("/{entityType}/{mappedId}")
    public ResponseEntity<List<Image>> getImages(
            @PathVariable String entityType,
            @PathVariable Long mappedId) {
        return ResponseEntity.ok(imageService.getImagesByEntity(entityType, mappedId));
    }

    // Xoá ảnh theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok("Xoá ảnh thành công");
    }

    // Tải ảnh về (download)
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String filename) {
        Resource resource = imageService.downloadImage(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
