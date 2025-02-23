package com.sparta.tentenbackend.domain.menu.controller;

import com.sparta.tentenbackend.domain.menu.dto.MenuOptionDto;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.service.MenuOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menu-options")
@RequiredArgsConstructor
public class MenuOptionController {

  private final MenuOptionService menuOptionService;

  // 메뉴 옵션 생성(엔티티 반환 → DTO 변환 고려 가능)
  @PostMapping("/{menuId}")
  public ResponseEntity<MenuOption> createMenuOption(@PathVariable UUID menuId, @RequestBody MenuOption menuOption) {
    return ResponseEntity.ok(menuOptionService.createMenuOption(menuId, menuOption));
  }

  // 특정 메뉴의 옵션 목록 조회(DTO 반환)
  @GetMapping("/{menuId}")
  public ResponseEntity<List<MenuOptionDto>> getMenuOptionsByMenu(@PathVariable UUID menuId) {
    List<MenuOptionDto> menuOptions = menuOptionService.getMenuOptionsByMenu(menuId);
    return ResponseEntity.ok(menuOptions);
  }

  // 메뉴 옵션 삭제(숨김 처리)
  @DeleteMapping("/{menuOptionId}")
  public ResponseEntity<String> deleteMenuOption(@PathVariable UUID menuOptionId,
      @RequestParam String deletedBy) {
    menuOptionService.deleteMenuOption(menuOptionId, deletedBy);
    return ResponseEntity.ok("메뉴 옵션이 숨김 처리되었습니다.");
  }
}