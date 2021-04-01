package com.lacnguyen.recipeserver.service;


import com.lacnguyen.recipeserver.entity.RecipeEntity;

import java.util.Optional;

public interface IRecipeService {

    Optional<RecipeEntity> findById(Long id);
}
