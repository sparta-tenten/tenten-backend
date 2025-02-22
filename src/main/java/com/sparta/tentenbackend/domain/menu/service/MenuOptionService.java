package com.sparta.tentenbackend.domain.menu.service;

import com.sparta.tentenbackend.domain.menu.dto.MenuOptionDto;
import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.repository.MenuOptionRepository;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import java.util.stream.Collectors;
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

  // 특정 메뉴의 옵션 목록 조회 (숨김 처리 반영, DTO 변환)
  @Transactional(readOnly = true)
  public List<MenuOptionDto> getMenuOptionsByMenu(UUID menuId) {
    return menuOptionRepository.findByMenuIdAndIsDeletedFalse(menuId)
        .stream()
        .map(MenuOptionDto::fromEntity)  // 엔티티를 DTO로 변환
        .collect(Collectors.toList());
  }

  // 메뉴 옵션 숨김 처리 (Soft Delete)
  @Transactional
  public void deleteMenuOption(UUID menuOptionId, String deletedBy) {
    MenuOption menuOption = menuOptionRepository.findById(menuOptionId)
        .orElseThrow(() -> new IllegalArgumentException("메뉴 옵션을 찾을 수 없습니다. "));

    menuOption.setDeleted(true);  // 숨김 처리
    menuOption.setDeletedAt(new java.util.Date());  // 삭제 시간 설정
    menuOption.setDeletedBy(deletedBy);  // 삭제한 사람 설정

    menuOptionRepository.save(menuOption);  // 변경 사항 저장
  }
}