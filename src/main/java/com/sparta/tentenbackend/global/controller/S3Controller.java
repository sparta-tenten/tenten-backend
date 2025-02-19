package com.sparta.tentenbackend.global.controller;

import com.sparta.tentenbackend.global.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

  private final S3Service s3Service;

  public S3Controller(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  // 파일 업로드
  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      String url = s3Service.uploadFile(file);
      return ResponseEntity.ok("File uploaded successfully: " + url);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
    }
  }

  // 파일 수정 - 파일을 대체하는 거라 파일명이 바뀌진 않음.
  @PutMapping("/update/{fileName}")
  public ResponseEntity<String> updateFile(@PathVariable String fileName, @RequestParam("file") MultipartFile file) {
    try {
      String url = s3Service.updateFile(fileName, file);
      return ResponseEntity.ok("File updated successfully: " + url);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to update file: " + e.getMessage());
    }
  }

  // 파일 삭제
  @DeleteMapping("/delete/{fileName}")
  public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    try {
      s3Service.deleteFile(fileName);
      return ResponseEntity.ok("File deleted successfully: " + fileName);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to delete file: " + e.getMessage());
    }
  }
}
