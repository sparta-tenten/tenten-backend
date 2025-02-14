package com.sparta.tentenbackend.domain.category.controller;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  // 카테고리 추가
  @PostMapping("/master/category")
  public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto) {
//  public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return categoryService.addCategory(requestDto);
//    return categoryService.addCategory(requestDto, userDetails.getUser());
  }
}
