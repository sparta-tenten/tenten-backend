package com.sparta.tentenbackend.domain.region.service;


import com.sparta.tentenbackend.domain.region.repository.RegionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;


}
