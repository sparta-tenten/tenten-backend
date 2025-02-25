package com.sparta.tentenbackend.domain.region.service;

import com.sparta.tentenbackend.domain.region.entity.Town;

public interface TownService {

    Town getTownByCode(String code);
}
