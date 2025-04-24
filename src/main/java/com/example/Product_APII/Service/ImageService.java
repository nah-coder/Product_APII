package com.example.Product_APII.Service;

import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static final String UPLOAD_DIR = "uploads/";

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            Path tempFile = Files.createTempFile("upload_", file.getOriginalFilename());
            Files.write(tempFile, file.getBytes());
            String mimeType = Files.probeContentType(tempFile);

            if (mimeType == null) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Path.of(UPLOAD_DIR + fileName);

            if (!Files.exists(Paths.get(UPLOAD_DIR))) {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
            }

            Files.write(filePath, file.getBytes());
            String imageUrl = "/uploads/" + fileName;

            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    public ResponseEntity<Resource> downloadFile(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            // Xác định loại MIME dựa trên phần mở rộng của file
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            // Trả về file dưới dạng ResponseEntity
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        } catch (IOException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    // 1. GET ảnh theo tên file
    public ResponseEntity<Resource> getImage(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            // Xác định MIME type dựa trên phần mở rộng
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (IOException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    // 2. PUT Cập nhật ảnh (xóa ảnh cũ và lưu ảnh mới)
    public String updateImage(String oldFileName, MultipartFile newFile) throws IOException {
        // Xóa ảnh cũ
        deleteImage(oldFileName);

        // Upload ảnh mới
        return uploadFiles(Collections.singletonList(newFile)).get(0);
    }

    // 3. DELETE Xóa ảnh
    public void deleteImage(String fileName) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
        Files.deleteIfExists(filePath);
    }
}
