package com.sparta.tentenbackend.domain.category.controller;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  // 카테고리 추가
  @PostMapping("/master/category")
  public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto) {
    return categoryService.addCategory(requestDto);
  }

  // 카테고리 목록 전체 조회
  @GetMapping("/category")
  public List<CategoryResponseDto> getAllCategories() {
    return categoryService.findAllCategories();
  }

  // 카테고리 수정
  @PutMapping("/master/category")
  public CategoryResponseDto updateCategory(@RequestBody CategoryRequestDto requestDto) {
    return categoryService.modifyCategory(requestDto);
  }

  // 카테고리 삭제
  @DeleteMapping("/master/category")
  public String deleteCategory(@RequestBody CategoryRequestDto requestDto) {
    categoryService.removeCategory(requestDto);
    return "카테고리 삭제를 완료했습니다.";
  }

  /* user 구현 후
  // 카테고리 추가
  @PostMapping("/master/category")
  public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return categoryService.addCategory(requestDto, userDetails.getUser());
  }

  // 카테고리 수정
  @PutMapping("/master/category")
  public CategoryResponseDto updateCategory(@RequestBody CategoryRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return categoryService.modifyCategory(requestDto, userDetails.getUser());
  }
   */

}
