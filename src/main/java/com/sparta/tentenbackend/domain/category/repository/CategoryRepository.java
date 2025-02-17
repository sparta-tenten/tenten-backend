package com.sparta.tentenbackend.domain.category.repository;

import com.sparta.tentenbackend.domain.category.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
