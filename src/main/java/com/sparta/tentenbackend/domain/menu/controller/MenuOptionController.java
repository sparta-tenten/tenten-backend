package com.sparta.tentenbackend.domain.menu.controller;

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

  // 메뉴 옵션 생성
  @PostMapping("/{menuId}")
  public ResponseEntity<MenuOption> createMenuOption(@PathVariable UUID menuId, @RequestBody MenuOption menuOption) {
    return ResponseEntity.ok(menuOptionService.createMenuOption(menuId, menuOption));
  }

  // 메뉴 옵션 조회
  @GetMapping("/{menuId}")
  public ResponseEntity<List<MenuOption>> getMenuOptionsByMenu(@PathVariable UUID menuId) {
    return ResponseEntity.ok(menuOptionService.getMenuOptionsByMenu(menuId));
  }
}