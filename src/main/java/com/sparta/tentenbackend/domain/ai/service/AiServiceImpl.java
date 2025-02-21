package com.sparta.tentenbackend.domain.ai.service;

import com.sparta.tentenbackend.domain.ai.entity.Ai;
import com.sparta.tentenbackend.domain.ai.repository.AiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

  private final AiRepository aiRepository;
  private final GeminiService geminiService;

  @Override
  public String makeMenuDescription(String question) {
    String answer = geminiService.callGeminiApi(question);
    Ai ai = new Ai(question, answer);
    aiRepository.save(ai);
    return answer;
  }

}
