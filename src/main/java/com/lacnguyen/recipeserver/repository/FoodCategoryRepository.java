package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {
    Optional<FoodCategoryEntity> findByFoodCategoryId(Long id);
    Optional<FoodCategoryEntity> findByFoodCategoryName(String name);
}
