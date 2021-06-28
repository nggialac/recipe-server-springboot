package com.lacnguyen.recipeserver.service;


import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRecipeService {
    boolean isExistRecipe(Long id);

    Optional<RecipeEntity> findByRecipeId(Long id);

    RecipeEntity findARecipe(Long id);

    Collection<RecipeEntity> findListRecipe();

    Collection<RecipeEntity> findByRecipeNameContains();

    void removeARecipe(Long id);

    //
    ResponseEntity<Object> deleteRecipe_FC(Long id);
    ResponseEntity<Object> updateRecipe_FC(RecipeEntity recipe, Long id);
    ResponseEntity<Object> createRecipe_FC(RecipeEntity recipe);
}
