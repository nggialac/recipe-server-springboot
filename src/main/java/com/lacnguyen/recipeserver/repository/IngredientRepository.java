package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> findByRecipe_RecipeId(Long id);

    Optional<IngredientEntity> findByIngredientIdAndRecipe_RecipeId(Long id, Long recipeId);
}
