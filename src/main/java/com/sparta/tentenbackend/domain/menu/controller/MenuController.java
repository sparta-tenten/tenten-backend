package com.sparta.tentenbackend.domain.menu.controller;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

  private final MenuService menuService;

  // 메뉴 생성
  @PostMapping("/{storeId}")
  public ResponseEntity<Menu> createMenu(@PathVariable UUID storeId, @RequestBody @Valid Menu menu) {
    return ResponseEntity.ok(menuService.createMenu(storeId, menu));
  }

  // 가게별 메뉴 목록 조회
  @GetMapping("/{storeId}")
  public ResponseEntity<List<Menu>> getMenusByStore(@PathVariable UUID storeId) {
    return ResponseEntity.ok(menuService.getMenusByStore(storeId));
  }

  // 메뉴 상세 조회
  @GetMapping("/detail/{menuId}")
  public ResponseEntity<Menu> getMenuById(@PathVariable UUID menuId) {
    return ResponseEntity.ok(menuService.getMenuById(menuId));
  }

  // 메뉴 수정
  @PutMapping("/{menuId}")
  public ResponseEntity<Menu> updateMenu(@PathVariable UUID menuId, @RequestBody Menu menu) {
    return ResponseEntity.ok(menuService.updateMenu(menuId, menu));
  }

  // 메뉴 삭제
  @DeleteMapping("/{menuId}")
  public ResponseEntity<Void> deleteMenu(@PathVariable UUID menuId) {
    menuService.deleteMenu(menuId);
    return ResponseEntity.noContent().build();
  }
}
