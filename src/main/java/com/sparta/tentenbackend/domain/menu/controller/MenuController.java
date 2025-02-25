package com.sparta.tentenbackend.domain.menu.controller;

import com.sparta.tentenbackend.domain.menu.dto.MenuDto;
import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    Menu createdMenu = menuService.createMenu(storeId, menu);
    return ResponseEntity.status(201).body(createdMenu);  // 201 Created
  }

  // 가게별 메뉴 목록 조회
  @GetMapping("/{storeId}")
  public ResponseEntity<List<MenuDto>> getMenusByStore(@PathVariable UUID storeId) {
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


  // 검색/정렬/페이징 기능
  @GetMapping("/{storeId}/search")
  public ResponseEntity<Page<MenuDto>> searchMenus(
      @PathVariable UUID storeId,
      @RequestParam(required = false, defaultValue = "") String keyword,
      @RequestParam(required = false, defaultValue = "price") String sortBy,
      @RequestParam(required = false, defaultValue = "asc") String direction,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size
  ) {
    Page<MenuDto> menus = menuService.searchMenus(storeId, keyword, sortBy, direction, page, size);
    return ResponseEntity.ok(menus);
  }
}
