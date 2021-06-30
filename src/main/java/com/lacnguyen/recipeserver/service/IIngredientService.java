package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.IngredientEntity;

import java.util.List;
import java.util.Optional;

public interface IIngredientService {

    List<IngredientEntity> getAllIngredientByRecipeId(Long id);

    IngredientEntity save(IngredientEntity obj);

    Optional<IngredientEntity> getIngredientById(Long id);

    void deleteIngredient(IngredientEntity obj);

    Optional<IngredientEntity> findByIdAndRecipeId(Long id, Long recipeId);
}
