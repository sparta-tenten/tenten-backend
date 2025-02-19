package com.sparta.tentenbackend.domain.store.dto;

import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.region.entity.Town;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StoreRequestDto {
  @NotBlank(message = "가게 이름은 필수 항목입니다.")
  private String name;

  @NotBlank(message = "주소는 필수 항목입니다.")
  private String address;

  @NotBlank(message = "전화번호는 필수 항목입니다.")
  private String phoneNumber;


  private String image;

  @NotNull(message = "카테고리는 필수 항목입니다.")
  private UUID categoryId;

  @NotBlank(message = "동네는 필수 항목입니다.")
  private Town town;
}
