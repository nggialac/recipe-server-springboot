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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.lang.Long;

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

//    @GetMapping("/pagination")
//    public ResponseEntity<Page<RecipeEntity>> findListRecipe_Paginate(@RequestParam int pageSize,
//                                                                      @RequestParam int pageNumber) {
//        return new ResponseEntity<>(iRecipeService.findListRecipe_Paginate(pageNumber, pageSize), HttpStatus.OK);
//    }

    @GetMapping("/pagination")
    public ResponseEntity<Map<String, Object>> findListRecipe_Paginate(@RequestParam(required = false) String name,
                                                                       @RequestParam int pageSize,
                                                                       @RequestParam int pageNumber) {
        try {
            List<RecipeEntity> recipeList = new ArrayList<>();
            Page<RecipeEntity> recipePage;
            if (name == null)
                recipePage = iRecipeService.findListRecipe_Paginate(pageNumber, pageSize);
            else
                recipePage = iRecipeService.findByRecipeNameContains(name, pageNumber, pageSize);

            recipeList = recipePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("recipes", recipeList);
            response.put("currentPage", recipePage.getNumber());
            response.put("totalItems", recipePage.getTotalElements());
            response.put("totalPages", recipePage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination/desc")
    public ResponseEntity<Map<String, Object>> findListRecipe_Paginate_Desc(@RequestParam int pageSize,
                                                                            @RequestParam int pageNumber) {
        try {
            List<RecipeEntity> recipeList = new ArrayList<>();
            Page<RecipeEntity> recipePage;
            recipePage = iRecipeService.findListRecipe_Paginate_Desc(pageNumber, pageSize);

            recipeList = recipePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("recipes", recipeList);
            response.put("currentPage", recipePage.getNumber());
            response.put("totalItems", recipePage.getTotalElements());
            response.put("totalPages", recipePage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public List<CourseEntity> getAllCourseByRecipeId(@PathVariable(value = "id") Long id) {
        return iCourseService.findAllCourse(id);
    }

    @GetMapping("/{id}/course/pagination")
    public ResponseEntity<Map<String, Object>> getAllCoursePageableByRecipeId(@PathVariable(value = "id") Long id,
                                                                              @RequestParam(value = "pageNumber") int pageNumber,
                                                                              @RequestParam(value = "pageSize") int pageSize) {
        try {
            List<CourseEntity> courseList = new ArrayList<>();
            Page<CourseEntity> coursePage = iCourseService.findAllCourse(id, pageNumber, pageSize);
            courseList = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("courses", courseList);
            response.put("currentPage", coursePage.getNumber());
            response.put("totalItems", coursePage.getTotalElements());
            response.put("totalPages", coursePage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{id}/course/all")
//    public Optional<CourseEntity> getAllCourseByRecipeId(@PathVariable(value = "id") Long id) {
//        return iCourseService.getAllCourseByRecipeId(id);
//    }

    @PostMapping("/{id}/course")
    public CourseEntity createCourseByRecipeId(@PathVariable(value = "id") Long id,
                                               @Validated @RequestBody CourseEntity course) {
        return iRecipeService.findByRecipeId(id).map(recipe -> {
            course.setRecipe(recipe);
            return iCourseService.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " not found!"));
    }

    @PutMapping("/{recipeId}/course/{courseId}")
    public CourseEntity updateCourseByRecipeId(@PathVariable(value = "recipeId") Long recipeId,
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
    public ResponseEntity<?> deleteCourseByRecipeId(@PathVariable(value = "recipeId") Long recipeId,
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
    public IngredientEntity createIngredientByRecipeId(@PathVariable(value = "id") Long id,
                                                       @Validated @RequestBody IngredientEntity ingredient) {
        return iRecipeService.findByRecipeId(id).map(recipe -> {
            ingredient.setRecipe(recipe);
            return iIngredientService.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " not found!"));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/ingredient/{ingredientId}", method = RequestMethod.PUT)
    public IngredientEntity updateIngredientByRecipeId(@PathVariable(value = "id") Long recipeId,
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
    public ResponseEntity<?> deleteIngredientByRecipeId(@PathVariable(value = "recipeId") Long recipeId,
                                                        @PathVariable(value = "ingredientId") Long ingredientId) {
        return iIngredientService.findByIdAndRecipeId(ingredientId, recipeId).map(ingre -> {
            iIngredientService.deleteIngredient(ingre);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ingredient not found with id " + ingredientId + " and recipeId " + recipeId));
    }


    //////////////FOOD_CATEGORY/////////////
    @PostMapping("/create/fc")
    public ResponseEntity<Object> createRecipeWithFc(@RequestBody RecipeEntity recipe) {
        return iRecipeService.createRecipe_FC(recipe);
    }

    @GetMapping("/details/{id}/fc")
    public RecipeEntity getRecipeWithFc(@PathVariable Long id) {
        if (recipeRepository.findById(id).isPresent())
            return recipeRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/all/fc")
    public List<RecipeEntity> getAllRecipeWithFc() {
        return recipeRepository.findAll();
    }

    @PutMapping("/update/{id}/fc")
    public ResponseEntity<Object> updateRecipeWithFc(@PathVariable Long id, @RequestBody RecipeEntity recipe) {
        return iRecipeService.updateRecipe_FC(recipe, id);
    }

    @DeleteMapping("/delete/{id}/fc")
    public ResponseEntity<Object> deleteRecipeWithFc(@PathVariable Long id) {
        return iRecipeService.deleteRecipe_FC(id);
    }
}