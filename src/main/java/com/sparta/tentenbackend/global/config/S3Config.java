package com.sparta.tentenbackend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

  @Value("${aws.access.key.id}")
  private String accessKeyId;

  @Value("${aws.secret.access.key}")
  private String secretAccessKey;

  @Value("${aws.region}")
  private String region;

  @Bean
  public S3Client s3Client() {
    // AWS 자격 증명 생성
    AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
    // S3Client 빌더에서 자격 증명과 리전 설정
    return S3Client.builder()
        .region(Region.of(region))  // AWS 리전 설정
        .credentialsProvider(StaticCredentialsProvider.create(awsCredentials)) // 자격 증명 추가
        .build();
  }
}
