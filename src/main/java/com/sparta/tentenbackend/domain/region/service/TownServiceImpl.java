package com.sparta.tentenbackend.domain.region.service;

import com.sparta.tentenbackend.domain.region.entity.Town;
import com.sparta.tentenbackend.domain.region.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Override
    @Transactional(readOnly = true)
    public Town getTownByCode(String code) {
        return townRepository.findTownByCode(code);
    }
}
