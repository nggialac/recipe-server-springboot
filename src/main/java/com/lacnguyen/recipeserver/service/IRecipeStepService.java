package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

public interface IRecipeStepService {
    Optional<RecipeStepEntity> findByRecipeId(Long id);

    Optional<RecipeStepEntity> findByRecipeStepId(Long id);

    RecipeStepEntity save(RecipeStepEntity objCourse);

    void deleteStep(Long id);

    //List<RecipeStepEntity> findByRecipeStepContains(String name);

    void updateRecipeStep(RecipeStepEntity objCourse);

    //RecipeStepEntity findByRecipeId(Long id);
}
