package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeStepRepository extends JpaRepository<RecipeStepEntity, Long> {
    Optional<RecipeStepEntity> findByRecipe_RecipeId(Long recipeId);
    //Optional<RecipeStepEntity> findByIdAndRecipeId(Long id, Long recipeId);

//    void findByStepId()
    void deleteByStepId(Long id);
}
