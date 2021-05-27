package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService implements IRecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public Optional findByRecipeId(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Collection<RecipeEntity> findListRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Collection<RecipeEntity> findByRecipeNameContains() {
        return recipeRepository.findAll();
    }

    @Override
    public void removeARecipe(Long id) {
        recipeRepository.deleteById(id);
    }


}
