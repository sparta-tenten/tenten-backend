package com.sparta.tentenbackend.domain.ai.controller;

import com.sparta.tentenbackend.domain.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

  private final AiService aiService;

  @PostMapping
  public ResponseEntity<String> generateMenuDescription(@RequestBody String question) {
    String answer = aiService.makeMenuDescription(question);
    return ResponseEntity.ok(answer);
  }
}
