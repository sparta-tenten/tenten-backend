package com.sparta.tentenbackend.domain.store.dto;

import com.sparta.tentenbackend.domain.store.entity.Store;

import lombok.Getter;

import java.util.UUID;

@Getter
public class StoreResponseDto {

  private UUID id;
  private String name;
  private String address;
  private String phoneNumber;
  private String image;

  public StoreResponseDto(Store store) {
    this.id = store.getId();
    this.name = store.getName();
    this.address = store.getAddress();
    this.phoneNumber = store.getPhoneNumber();
    this.image = store.getImage();
  }
}