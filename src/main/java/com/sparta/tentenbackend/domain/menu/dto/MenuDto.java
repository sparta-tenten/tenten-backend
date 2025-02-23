package com.sparta.tentenbackend.domain.menu.dto;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import java.math.BigInteger;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDto {
  private UUID id;
  private String name;
  private Long price;
  private String description;

  public static MenuDto fromEntity(Menu menu) {
    return new MenuDto(
        menu.getId(),
        menu.getName(),
        menu.getPrice(),
        menu.getDescription()
    );
  }
}

