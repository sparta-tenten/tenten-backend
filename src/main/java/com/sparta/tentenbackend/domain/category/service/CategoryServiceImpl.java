package com.sparta.tentenbackend.domain.category.service;

import com.sparta.tentenbackend.domain.category.dto.CategoryRequestDto;
import com.sparta.tentenbackend.domain.category.dto.CategoryResponseDto;
import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.category.repository.CategoryRepository;
import com.sparta.tentenbackend.domain.user.entity.User;
import com.sparta.tentenbackend.domain.user.entity.UserRoleEnum;
import com.sparta.tentenbackend.global.exception.ForbiddenException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  // 카테고리 추가
  @Override
  public CategoryResponseDto addCategory(CategoryRequestDto requestDto, User user) {
    if (user.getRole() == UserRoleEnum.MASTER) {
      Category category = categoryRepository.save(new Category(requestDto));
      return new CategoryResponseDto(category);
    } else {
      throw new ForbiddenException("카테고리를 생성할 권한이 없습니다.");
    }
  }

  // 카테고리 목록 조회
  @Override
  public List<CategoryResponseDto> findAllCategories() {
    List<Category> categoryList = categoryRepository.findAllByIsDeletedFalse();
    List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
    for (Category category : categoryList) {
      categoryResponseDtoList.add(new CategoryResponseDto(category));
    }
    return categoryResponseDtoList;
  }

  // 카테고리 수정
  @Override
  public CategoryResponseDto modifyCategory(CategoryRequestDto requestDto, User user) {
    Category category = categoryRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NullPointerException("해당 카테고리는 존재하지 않습니다."));
    if (user.getRole() == UserRoleEnum.MASTER) {
      category.updateById(requestDto);
      categoryRepository.save(category);
      return new CategoryResponseDto(category);
    } else {
      throw new ForbiddenException("카테고리를 생성할 권한이 없습니다.");
    }
  }

  // 카테고리 삭제
  @Override
  public void removeCategory(CategoryRequestDto requestDto, User user) {
    Category category = categoryRepository.findById(requestDto.getId()).orElseThrow(() ->
        new NullPointerException("해당 카테고리는 존재하지 않습니다."));
    if (user.getRole() == UserRoleEnum.MASTER) {
      category.markAsDeleted(); // 카테고리의 isDeleted true
      categoryRepository.save(category);
    } else {
      throw new ForbiddenException("카테고리를 생성할 권한이 없습니다.");
    }
  }
}
