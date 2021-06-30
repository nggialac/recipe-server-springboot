package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.IngredientEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.entity.RecipeStepEntity;
import com.lacnguyen.recipeserver.models.ResourceNotFoundException;
import com.lacnguyen.recipeserver.repository.IngredientRepository;
import com.lacnguyen.recipeserver.repository.RecipeRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import com.lacnguyen.recipeserver.service.IIngredientService;
import com.lacnguyen.recipeserver.service.IRecipeService;
import com.lacnguyen.recipeserver.service.IRecipeStepService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private IRecipeStepService iRecipeStepService;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IIngredientService iIngredientService;

    @GetMapping
    public ResponseEntity<Collection<RecipeEntity>> findListRecipe() {
        return new ResponseEntity<>(recipeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeEntity> findRecipeById(@PathVariable("id") Long id) {
        Optional<RecipeEntity> recipeOptional = iRecipeService.findByRecipeId(id);
        return recipeOptional.map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<RecipeEntity> newRecipe(@RequestBody RecipeEntity newRecipe) {
        return new ResponseEntity<>(recipeRepository.save(newRecipe), HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<RecipeEntity> updateRecipe(@PathVariable("id") long id, @RequestBody RecipeEntity recipe) {
        Optional<RecipeEntity> recipeData = recipeRepository.findById(id);

        if (recipeData.isPresent()) {
            RecipeEntity _recipe = recipeData.get();
            _recipe.setRecipeName(recipe.getRecipeName());
            _recipe.setRecipeDescription(recipe.getRecipeDescription());
            _recipe.setPrepTime(recipe.getPrepTime());
            _recipe.setCookTime(recipe.getCookTime());
            _recipe.setRecipeImage(recipe.getRecipeImage());
//            _recipe.setFoodCategories(recipe.getFoodCategories());
            return new ResponseEntity<>(recipeRepository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @CrossOrigin
//    @DeleteMapping("/{id}")
//    public void deleteRecipeEntity(@PathVariable("id") Long id) {
//        recipeRepository.deleteById(id);
//    }

    @DeleteMapping
    public void deleteAllRecipe() {
        recipeRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecipeEntity> deleteCategory(@PathVariable Long id) {
        Optional<RecipeEntity> recipeOptional = iRecipeService.findByRecipeId(id);
        return recipeOptional.map(recipe -> {
            iRecipeService.removeARecipe(id);
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/recipename")
    public List<RecipeEntity> findRecipeByName(@RequestParam("name") String name) {
        return recipeRepository.findByRecipeNameContains(name.trim());
    }


    /////////////////////// RECIPE-STEP
    @GetMapping("/{id}/recipestep")
    public Optional<RecipeStepEntity> findByRecipeId(@PathVariable("id") Long id) {
        return iRecipeStepService.findByRecipeId(id);
    }

    @PostMapping("/{id}/recipestep")
    public RecipeStepEntity createRecipeStep(@PathVariable("id") Long id, @Validated @RequestBody RecipeStepEntity recipeStep) {

        return iRecipeService.findByRecipeId(id).map(recipe -> {
            recipeStep.setRecipe((RecipeEntity) recipe);
            return iRecipeStepService.save(recipeStep);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

//    @PutMapping("/posts/{postId}/comments/{commentId}")
//    public Comment updateComment(@PathVariable (value = "postId") Long postId,
//                                 @PathVariable (value = "commentId") Long commentId,
//                                 @Valid @RequestBody Comment commentRequest) {
//        if(!postRepository.existsById(postId)) {
//            throw new ResourceNotFoundException("PostId " + postId + " not found");
//        }
//
//        return commentRepository.findById(commentId).map(comment -> {
//            comment.setText(commentRequest.getText());
//            return commentRepository.save(comment);
//        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
//    }

    @PutMapping("/{id}/recipestep")
    public RecipeStepEntity updateRecipeStep(@PathVariable(value = "id") Long id,
                                             @Validated @RequestBody RecipeStepEntity recipeStepRequest) {
        if (!iRecipeService.isExistRecipe(id)) {
            throw new ResourceNotFoundException("Not found");
        }

        return iRecipeStepService.findByRecipeId(id).map(recipestep -> {
            recipestep.setStepDescription(recipeStepRequest.getStepDescription());
            return iRecipeStepService.save(recipestep);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @DeleteMapping("/{id}/recipestep")
    public void deleteRecipeStep(@PathVariable(value = "id") Long id) {
        iRecipeStepService.deleteStep(id);
    }


    ///////////////////////////////////////RECIPE-COURSE//////////
    @GetMapping("/{id}/course")
    public Optional<CourseEntity> getAllCourseByRecipeId(@PathVariable(value = "id") Long id) {
        return iCourseService.getAllCourseByRecipeId(id);
    }

    @PostMapping("/{id}/course")
    public CourseEntity createCourse(@PathVariable(value = "id") Long id,
                                     @Validated @RequestBody CourseEntity course) {
        return iRecipeService.findByRecipeId(id).map(recipe -> {
            course.setRecipe(recipe);
            return iCourseService.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " not found!"));
    }

    @PutMapping("/{recipeId}/course/{courseId}")
    public CourseEntity updateCourse(@PathVariable(value = "recipeId") Long recipeId,
                                     @PathVariable(value = "courseId") Long courseId,
                                     @Validated @RequestBody CourseEntity courseRequest) {
        if (!iRecipeService.isExistRecipe(recipeId)) {
            throw new ResourceNotFoundException("Recipe ID " + recipeId + " not found");
        }

        return iCourseService.findByCourseId(courseId).map(course -> {
            course.setCourseName(courseRequest.getCourseName());
            course.setCourseContent(courseRequest.getCourseContent());
            course.setCourseImage(courseRequest.getCourseImage());
            return iCourseService.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("courseId " + courseId + "not found"));
    }

    @DeleteMapping("/{recipeId}/course/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable(value = "recipeId") Long recipeId,
                                          @PathVariable(value = "courseId") Long courseId) {
        return iCourseService.findByIdAndRecipeId(courseId, recipeId).map(course -> {
            iCourseService.deleteById(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId + " and recipeId " + recipeId));
    }


    /////////////////////RECIPE-INGREDIENT/////////////////////

    @GetMapping("/{id}/ingredient")
    public List<IngredientEntity> getAllByRecipeId(@PathVariable(value = "id") Long id) {
        return iIngredientService.getAllIngredientByRecipeId(id);
    }

    @PostMapping("/{id}/ingredient")
    public IngredientEntity createIngredient(@PathVariable(value = "id") Long id,
                                             @Validated @RequestBody IngredientEntity ingredient) {
        return iRecipeService.findByRecipeId(id).map(recipe -> {
            ingredient.setRecipe(recipe);
            return iIngredientService.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " not found!"));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/ingredient/{ingredientId}", method = RequestMethod.PUT)
    public IngredientEntity updateIngredient(@PathVariable(value = "id") Long recipeId,
                                             @PathVariable(value = "ingredientId") Long ingredientId,
                                             @Validated @RequestBody IngredientEntity ingredientRequest) {
        if (!iRecipeService.isExistRecipe(recipeId)) {
            throw new ResourceNotFoundException("Recipe ID " + recipeId + " not found");
        }

        return iIngredientService.getIngredientById(ingredientId).map(ingredient -> {
            ingredient.setIngredientName(ingredientRequest.getIngredientName());
            ingredient.setMeasurement(ingredientRequest.getMeasurement());
            ingredient.setIngredientQuantity(ingredientRequest.getIngredientQuantity());
            return iIngredientService.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException("ingredientId " + ingredientId + "not found"));
    }

    @DeleteMapping("/{recipeId}/ingredient/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(value = "recipeId") Long recipeId,
                                              @PathVariable(value = "ingredientId") Long ingredientId) {
        return iIngredientService.findByIdAndRecipeId(ingredientId, recipeId).map(ingre -> {
            iIngredientService.deleteIngredient(ingre);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ingredient not found with id " + ingredientId + " and recipeId " + recipeId));
    }


    //////////////FOOD_CATEGORY/////////////
    @PostMapping("/create/fc")
    public ResponseEntity<Object> createUser(@RequestBody RecipeEntity recipe) {
        return iRecipeService.createRecipe_FC(recipe);
    }

    @GetMapping("/details/{id}/fc")
    public RecipeEntity getUser(@PathVariable Long id) {
        if (recipeRepository.findById(id).isPresent())
            return recipeRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/all/fc")
    public List<RecipeEntity> getUsers() {
        return recipeRepository.findAll();
    }

    @PutMapping("/update/{id}/fc")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody RecipeEntity recipe) {
        return iRecipeService.updateRecipe_FC(recipe, id);
    }

    @DeleteMapping("/delete/{id}/fc")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return iRecipeService.deleteRecipe_FC(id);
    }
}