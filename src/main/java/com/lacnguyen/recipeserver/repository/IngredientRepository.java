package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> findAllByRecipe_RecipeId(Long id);

    Optional<IngredientEntity> findByIngredientIdAndRecipe_RecipeId(Long id, Long recipeId);

    @Query("SELECT i FROM  IngredientEntity i WHERE i.recipe.recipeId=:recipeId")
    List<IngredientEntity> findAll(@Param("recipeId") Long recipeId);

    Optional<IngredientEntity> findByIngredientName(String name);
}
