package com.lacnguyen.recipeserver.api;


import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.IRecipeService;
import com.lacnguyen.recipeserver.service.IRecipeStepService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "RecipeStep APIs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/recipestep")
public class RecipeStepApi {

    @Autowired
    private IRecipeStepService iRecipeStepService;

    @GetMapping
    public ResponseEntity<List<RecipeStepEntity>> findListRecipeStep() {
        return new ResponseEntity<>(iRecipeStepService.findAllRecipeStep(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeStepEntity> findRecipeStepById(@PathVariable("id") Long id) {
        Optional<RecipeStepEntity> recipeStepOptional = iRecipeStepService.findByRecipeStepId(id);
        return recipeStepOptional.map(recipeStep -> new ResponseEntity<>(recipeStep, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<RecipeStepEntity> newRecipeStep(@RequestBody RecipeStepEntity newRecipe) {
        return new ResponseEntity<>(iRecipeStepService.save(newRecipe), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<RecipeStepEntity> updateRecipeStep(@PathVariable("id") long id, @RequestBody RecipeStepEntity recipe) {
        Optional<RecipeStepEntity> recipeStepData = iRecipeStepService.findByRecipeStepId(id);
        if (recipeStepData.isPresent()) {
            RecipeStepEntity _recipeStep = recipeStepData.get();
            _recipeStep.setStepNumber(recipe.getStepNumber());
            _recipeStep.setStepDescription(recipe.getStepDescription());
            return new ResponseEntity<>(iRecipeStepService.save(_recipeStep), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeStepEntity> deleteRecipeStepEntity(@PathVariable("id") Long id) {
        Optional<RecipeStepEntity> recipeStepOptional = iRecipeStepService.findByRecipeStepId(id);
        return recipeStepOptional.map(recipeStep -> {
            iRecipeStepService.deleteByRecipeStepId(id);
            return new ResponseEntity<>(recipeStep, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

