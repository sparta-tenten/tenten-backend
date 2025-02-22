package com.sparta.tentenbackend.domain.menu.service;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.repository.MenuOptionRepository;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

  private final MenuOptionRepository menuOptionRepository;
  private final MenuRepository menuRepository;

  // 메뉴 옵션 생성
  @Transactional
  public MenuOption createMenuOption(UUID menuId, MenuOption menuOption) {
    Menu menu = menuRepository.findById(menuId)
        .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
    menuOption.setMenu(menu);
    return menuOptionRepository.save(menuOption);
  }

  // 특정 메뉴의 옵션 목록 조회 ()
  @Transactional(readOnly = true)
  public List<MenuOption> getMenuOptionsByMenu(UUID menuId) {
    return menuOptionRepository.findByMenuId(menuId);
  }
}