package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

@Api(value = "Recipe APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/recipe")
public class RecipeApi {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IRecipeService iRecipeService;

    @GetMapping
    public Collection<RecipeEntity> findListRecipe() {
        return iRecipeService.findListRecipe();
    }

    @GetMapping("/{id}")
    public RecipeEntity findRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findByRecipeId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public RecipeEntity newRecipe(@RequestBody RecipeEntity newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<RecipeEntity> updateTutorial(@PathVariable("id") long id, @RequestBody RecipeEntity recipe) {
        Optional<RecipeEntity> recipeData = recipeRepository.findById(id);

        if (recipeData.isPresent()) {
            RecipeEntity _recipe = recipeData.get();
            _recipe.setRecipeName(recipe.getRecipeName());
            _recipe.setRecipeDescription(recipe.getRecipeDescription());
            _recipe.setPrepTime(recipe.getPrepTime());
            _recipe.setCookTime(recipe.getCookTime());
            _recipe.setRecipeImage(recipe.getRecipeImage());
            return new ResponseEntity<>(recipeRepository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteRecipeEntity(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllRecipe() {
        recipeRepository.deleteAll();
    }

    @GetMapping("/recipename")
    public List<RecipeEntity> findRecipeByName(@RequestParam("name") String name) {
        return recipeRepository.findByRecipeNameContains(name.trim());
    }

}