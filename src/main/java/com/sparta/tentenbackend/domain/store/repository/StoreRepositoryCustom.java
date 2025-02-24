package com.sparta.tentenbackend.domain.store.repository;

import com.sparta.tentenbackend.domain.store.entity.Store;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryCustom {
  Page<Store> findStoresByCategory(UUID categoryId, String storeName, String sortBy, boolean isAsc, Pageable pageable);
}
