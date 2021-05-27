package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeStepRepository extends JpaRepository<RecipeStepEntity, Long> {

}
