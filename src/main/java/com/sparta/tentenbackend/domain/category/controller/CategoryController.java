package com.sparta.tentenbackend.domain.category.controller;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.service.CategoryService;
import com.sparta.tentenbackend.domain.user.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 추가
    @PostMapping("/master/category")
    public ResponseEntity<CategoryResponseDto> createCategory(
        @RequestBody CategoryRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CategoryResponseDto response = categoryService.addCategory(requestDto,
            userDetails.getUser());
        return ResponseEntity.ok(response);
    }

    // 카테고리 목록 전체 조회
    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> response = categoryService.findAllCategories();
        return ResponseEntity.ok(response);
    }

    // 카테고리 수정
    @PutMapping("/master/category")
    public ResponseEntity<CategoryResponseDto> updateCategory(
        @RequestBody CategoryRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CategoryResponseDto response = categoryService.modifyCategory(requestDto,
            userDetails.getUser());
        return ResponseEntity.ok(response);
    }

    // 카테고리 삭제
    @DeleteMapping("/master/category")
    public ResponseEntity<String> deleteCategory(@RequestParam("categoryId") String categoryId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        categoryService.removeCategory(categoryId, userDetails.getUser());
        return ResponseEntity.ok("카테고리 삭제를 완료했습니다.");
    }
}
