package com.sparta.tentenbackend.domain.category.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    // 카테고리 ID
    private String id;
    // 카테고리 이름
    private String name;

    // ID 데이터타입 String to ValidUUID
    public UUID getId() {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
