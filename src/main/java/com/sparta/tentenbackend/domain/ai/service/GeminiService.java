package com.sparta.tentenbackend.domain.ai.service;

import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

  private final RestTemplate restTemplate;
  private final String API_KEY;
  private final String MODEL_NAME;

  public GeminiService(RestTemplate restTemplate, @Value("${gemini.api.key}") String API_KEY, @Value("${gemini.model.name}") String MODEL_NAME) {
    this.restTemplate = restTemplate;
    this.API_KEY = API_KEY;
    this.MODEL_NAME = MODEL_NAME;
  }

  public String callGeminiApi(String prompt) {
    String formattedPrompt = prompt + " (에 대한 답변을 50자 이하로 작성해주세요.)";
    String url = "https://generativelanguage.googleapis.com/v1beta/models/" + MODEL_NAME + ":generateContent?key=" + API_KEY;

    // JSON 요청 바디 생성
    Map<String, Object> requestBody = Map.of(
        "contents", Collections.singletonList(Map.of(
            "parts", Collections.singletonList(Map.of("text", formattedPrompt))
        ))
    );

    // HTTP 요청 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // HTTP 요청 객체 생성
    HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

    // API 호출
    ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

    // 응답 파싱
    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
      // 응답 바디에서 text만 추출
      Map<String, Object> responseBody = response.getBody();
      var candidates = responseBody.get("candidates");
      if (candidates instanceof java.util.List) {
        Map<String, Object> candidate = (Map<String, Object>) ((java.util.List) candidates).get(0);
        Map<String, Object> content = (Map<String, Object>) candidate.get("content");
        var parts = content.get("parts");
        if (parts instanceof java.util.List) {
          Map<String, String> part = (Map<String, String>) ((java.util.List) parts).get(0);
          return part.get("text"); // 'text' 값 반환
        }
      }
      throw new RuntimeException("API 응답에서 'text' 값을 찾을 수 없습니다.");
    } else {
      throw new RuntimeException("API 호출 실패: " + response.getStatusCode());
    }
  }
}
