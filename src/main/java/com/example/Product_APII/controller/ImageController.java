package com.example.Product_APII.controller;

import com.example.Product_APII.DTO.Request.ApiResponse;
import com.example.Product_APII.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(imageService.uploadFiles(files));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        return imageService.downloadFile(filename);
    }

    // 1. API GET ảnh theo tên
    @GetMapping("/get/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        return imageService.getImage(fileName);
    }

    // API PUT Cập nhật ảnh (Xóa ảnh cũ và upload ảnh mới)
    @PutMapping(value = "/update/{oldFileName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> updateImage(
            @PathVariable String oldFileName,
            @RequestParam("newFile") MultipartFile newFile) throws IOException {

        // Xử lý cập nhật ảnh: xóa ảnh cũ và upload ảnh mới
        String updatedFileUrl = imageService.updateImage(oldFileName, newFile);

        // Tạo response và trả lại
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(updatedFileUrl);
        return ResponseEntity.ok(apiResponse);
    }

    // 3. API DELETE Xóa ảnh
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteImage(@PathVariable String fileName) throws IOException {
        imageService.deleteImage(fileName);
        return ResponseEntity.ok("File " + fileName + " đã được xóa");
    }
}
