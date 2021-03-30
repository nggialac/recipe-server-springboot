package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RecipeApi {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipe")
    public Collection<RecipeEntity> findBooks() {
        return recipeRepository.findAll();
    }

}
