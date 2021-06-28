package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import com.lacnguyen.recipeserver.entity.ResponseEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.repository.RecipeStepRepository;
import com.lacnguyen.recipeserver.service.IRecipeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeStepService implements IRecipeStepService {

    @Autowired
    RecipeStepRepository recipeStepRepository;

    @Override
    public Optional<RecipeStepEntity> findByRecipeId(Long id) {
        return recipeStepRepository.findByRecipe_RecipeId(id);
    }

    @Override
    public Optional<RecipeStepEntity> findByRecipeStepId(Long id) {
        return recipeStepRepository.findById(id);
    }

    @Override
    public RecipeStepEntity save(RecipeStepEntity objCourse) {
        return recipeStepRepository.save(objCourse);
    }

    @Override
    public void deleteStep(Long id) {
        recipeStepRepository.deleteByStepId(id);
    }

    @Override
    public void updateRecipeStep(RecipeStepEntity objCourse) {
        recipeStepRepository.save(objCourse);
    }
}
