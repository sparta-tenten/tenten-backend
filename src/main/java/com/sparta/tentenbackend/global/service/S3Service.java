package com.sparta.tentenbackend.global.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

  private final S3Client s3Client;
  private final String bucketName;
  private final String region;

  public S3Service(S3Client s3Client, @Value("${aws.s3.bucket.name}") String bucketName, @Value("${aws.region}") String region) {
    this.s3Client = s3Client;
    this.bucketName = bucketName;
    this.region = region;
  }

  // 파일 업로드
  public String uploadFile(MultipartFile file) throws IOException {
    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .contentType(file.getContentType())
        .acl(ObjectCannedACL.PUBLIC_READ) // 공개 접근 가능 설정
        .build();

    s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

    return getFileUrl(fileName);
  }

  // 파일 수정 (대체 업로드)
  public String updateFile(String fileName, MultipartFile file) throws IOException {
    if (!fileExists(fileName)) {
      throw new IllegalArgumentException("File does not exist: " + fileName);
    }

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .contentType(file.getContentType())
        .acl(ObjectCannedACL.PUBLIC_READ)
        .build();

    s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

    return getFileUrl(fileName);
  }

  // 파일 삭제
  public void deleteFile(String fileName) {
    if (!fileExists(fileName)) {
      throw new IllegalArgumentException("File does not exist: " + fileName);
    }
    System.out.println(fileName);
    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .build();

    s3Client.deleteObject(deleteObjectRequest);
  }

  // 파일 존재 여부 확인
  private boolean fileExists(String fileName) {
    try {
      s3Client.headObject(HeadObjectRequest.builder()
          .bucket(bucketName)
          .key(fileName)
          .build());
      return true;
    } catch (S3Exception e) {
      System.out.println("파일 존재 확인 실패: " + e.awsErrorDetails().errorMessage());
      return false;
    }
  }

  // S3 파일 URL 반환
  private String getFileUrl(String fileName) {
    return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
  }
}
