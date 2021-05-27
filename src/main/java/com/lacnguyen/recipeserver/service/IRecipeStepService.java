package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.RecipeStepEntity;

import java.util.List;
import java.util.Optional;

public interface IRecipeStepService {

    List<RecipeStepEntity> findAllRecipeStep();

    Optional<RecipeStepEntity> findByRecipeStepId(Long id);

    RecipeStepEntity save(RecipeStepEntity objCourse);

    void deleteByRecipeStepId(Long id);

    //List<RecipeStepEntity> findByRecipeStepContains(String name);

    void updateRecipeStep(RecipeStepEntity objCourse);

    //RecipeStepEntity findByRecipeId(Long id);
}
