package com.sparta.tentenbackend.domain.menu.dto;

import com.sparta.tentenbackend.domain.menu.entity.MenuOption;

import java.math.BigInteger;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOptionDto {


  private UUID id;

  private String category;
//  private BigInteger price;
  private Long price;

  // 생성 메서드 (엔티티 -> DTO)
  public static MenuOptionDto fromEntity(MenuOption menuOption) {
    return MenuOptionDto.builder()
        .id(menuOption.getId())
        .category(menuOption.getCategory())
        .price(menuOption.getPrice())
        .build();
  }
}
