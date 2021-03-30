package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    @Override
    List<RecipeEntity> findAll();
}
