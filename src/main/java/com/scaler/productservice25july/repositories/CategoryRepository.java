package com.scaler.productservice25july.repositories;

import com.scaler.productservice25july.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
