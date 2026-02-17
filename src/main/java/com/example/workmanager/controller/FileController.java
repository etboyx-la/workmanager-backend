package com.example.workmanager.controller;

import com.example.workmanager.model.ApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    @Value("${file.upload.path:/tmp/uploads}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<ApiResult<Map<String, String>>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "categoryId", defaultValue = "general") String categoryId,
            @RequestParam(value = "categoryName", defaultValue = "通用") String categoryName,
            @RequestParam(value = "remark", defaultValue = "") String remark,
            @RequestParam(value = "employeeId", defaultValue = "demo") String employeeId) {

        try {
            // 创建上传目录
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // 保存文件
            Path filePath = uploadDir.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 构建响应
            Map<String, String> result = new HashMap<>();
            result.put("id", UUID.randomUUID().toString());
            result.put("fileUrl", "/api/files/download/" + uniqueFilename);
            result.put("fileName", originalFilename);
            result.put("categoryId", categoryId);
            result.put("uploadedAt", String.valueOf(System.currentTimeMillis()));

            return ResponseEntity.ok(ApiResult.success("文件上传成功", result));

        } catch (IOException e) {
            return ResponseEntity.ok(ApiResult.error("文件上传失败: " + e.getMessage()));
        }
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename);
            byte[] fileBytes = Files.readAllBytes(filePath);
            
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                    .body(fileBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}