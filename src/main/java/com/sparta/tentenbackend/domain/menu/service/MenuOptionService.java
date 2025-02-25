package com.sparta.tentenbackend.domain.menu.service;

import com.sparta.tentenbackend.domain.menu.dto.MenuOptionDto;
import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.repository.MenuOptionRepository;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import com.sparta.tentenbackend.global.exception.BadRequestException;
import com.sparta.tentenbackend.global.exception.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;
    private final MenuRepository menuRepository;

    // 메뉴 옵션 생성
    @Transactional
    public MenuOption createMenuOption(UUID menuId, MenuOption menuOption) {
        Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new NotFoundException("메뉴를 찾을 수 없습니다."));
        if (menuOption.getName() == null || menuOption.getName().isEmpty()) {
            throw new BadRequestException("메뉴 옵션 이름을 입력해주세요.");
        }
        menuOption.setMenu(menu);
        return menuOptionRepository.save(menuOption);
    }

    // 특정 메뉴의 옵션 목록 조회 (숨김 처리 반영, DTO 변환)
    @Transactional(readOnly = true)
    public List<MenuOptionDto> getMenuOptionsByMenu(UUID menuId) {

        List<MenuOption> options = menuOptionRepository.findByMenuIdAndIsDeletedFalse(menuId);

        if (options.isEmpty()) {
            throw new NotFoundException("해당 메뉴에 등록된 옵션이 없습니다.");
        }

        return options.stream()
            .map(MenuOptionDto::fromEntity)
            .collect(Collectors.toList());
    }

    // 메뉴 옵션 숨김 처리 (Soft Delete)
    @Transactional
    public void deleteMenuOption(UUID menuOptionId, String deletedBy) {
        MenuOption menuOption = menuOptionRepository.findById(menuOptionId)
            .orElseThrow(() -> new NotFoundException("메뉴 옵션을 찾을 수 없습니다. "));

        // 개선된 markAsDeleted 메서드 사용
        menuOption.markAsDeleted(deletedBy);

        menuOptionRepository.save(menuOption);  // 변경 사항 저장
    }
}
