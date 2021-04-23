package com.lacnguyen.recipeserver.service;


import com.lacnguyen.recipeserver.entity.RecipeEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IRecipeService {

    List<RecipeEntity> findByRecipeId(Long id);

    Collection<RecipeEntity> findListRecipe();


}
