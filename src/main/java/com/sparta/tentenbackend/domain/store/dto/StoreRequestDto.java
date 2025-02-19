package com.sparta.tentenbackend.domain.store.dto;

import com.sparta.tentenbackend.domain.category.entity.Category;
import com.sparta.tentenbackend.domain.region.entity.Town;

import lombok.Getter;

import java.util.UUID;

@Getter
public class StoreRequestDto {

  private String name;
  private String address;
  private String phoneNumber;
  private String image;
  private UUID categoryId;
  private Town town;
}
