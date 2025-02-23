package com.sparta.tentenbackend.domain.menu.service;

import com.sparta.tentenbackend.domain.menu.dto.MenuDto;
import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import com.sparta.tentenbackend.domain.store.entity.Store;
import com.sparta.tentenbackend.domain.store.repository.StoreRepository;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuRepository menuRepository;
  private final StoreRepository storeRepository;

  // 메뉴 생성
  @Transactional
  public Menu createMenu(UUID storeId, Menu menu) {
    // menu가 null인지 확인
    if (menu == null) {
      throw new IllegalArgumentException("메뉴 정보가 없습니다.");
    }

    // storeId로 가게 조회
    Store store = storeRepository.findById(storeId)
        .orElseThrow(() -> new NotFoundException("해당 가게를 찾을 수 없습니다."));

    // menu에 store 정보 설정
    menu.setStore(store);

    // 메뉴 저장
    return menuRepository.save(menu);
  }

  // 가게별 메뉴 목록 조회
  @Transactional
  public List<MenuDto> getMenusByStore(UUID storeId) {
    return menuRepository.findByStoreIdAndDeletedFalse(storeId)
        .stream()
        .map(MenuDto::fromEntity)
        .collect(Collectors.toList());
  }

  // 메뉴 상세 조회
  @Transactional
  public Menu getMenuById(UUID menuId) {
    return menuRepository.findById(menuId)
        .orElseThrow(() -> new NotFoundException("메뉴를 찾을 수 없습니다."));
  }

  // 메뉴 수정
  @Transactional
  public Menu updateMenu(UUID menuId, Menu updatedMenu) {
    Menu menu = getMenuById(menuId);

    if (updatedMenu.getName() != null) {
      menu.setName(updatedMenu.getName());
    }
    if (updatedMenu.getPrice() != null) {
      menu.setPrice(updatedMenu.getPrice());
    }
    if (updatedMenu.getDescription() != null) {
      menu.setDescription(updatedMenu.getDescription());
    }
    if (updatedMenu.getImage() != null) {
      menu.setImage(updatedMenu.getImage());
    }

    return menuRepository.save(menu);
  }

  // 메뉴 삭제
  @Transactional
  public void deleteMenu(UUID menuId) {
    Menu menu = getMenuById(menuId);
    menu.markAsDeleted("삭제한 유저");
    menuRepository.save(menu);// 실제 삭제 대신 Soft Delete
  }

  //검색/정렬/페이징
  public Page<MenuDto> searchMenus(UUID storeId, String keyword, String sortBy, String direction, int page, int size) {
    Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Menu> menuPage = menuRepository.findByStoreIdAndDeletedFalseAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
        storeId, keyword, keyword, pageable
    );

    return menuPage.map(MenuDto::fromEntity);
  }
}
