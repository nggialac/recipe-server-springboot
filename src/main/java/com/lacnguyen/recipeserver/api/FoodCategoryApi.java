package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import com.lacnguyen.recipeserver.repository.FoodCategoryRepository;
import com.lacnguyen.recipeserver.service.IFoodCategoryService;
import com.lacnguyen.recipeserver.service.impl.FoodCategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Food Category APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/foodcategory")
public class FoodCategoryApi {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;

    @Autowired
    IFoodCategoryService iFoodCategoryService;

    @PostMapping("/create")
    public ResponseEntity<FoodCategoryEntity> createSingleFc(@RequestBody FoodCategoryEntity fc) {
        return new ResponseEntity<>(iFoodCategoryService.createFc(fc), HttpStatus.OK);
    }

    //Need to fix if FC existed
    @PostMapping("/create/recipe")
    public ResponseEntity<Object> createFC(@RequestBody FoodCategoryEntity fc) {
        return  iFoodCategoryService.addFoodCategory(fc);
    }
    @DeleteMapping("/delete/{id}/recipe")
    public ResponseEntity<Object> deleteFC(@PathVariable Long id) {
        return iFoodCategoryService.deleteFoodCategory(id);
    }
    @GetMapping("/details/{id}/recipe")
    public FoodCategoryEntity getFC(@PathVariable Long id) {
        if(foodCategoryRepository.findById(id).isPresent())
            return foodCategoryRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/all/recipe")
    public List<FoodCategoryEntity> getAllFc() {
        return foodCategoryRepository.findAll();
    }
    @PutMapping("/update/{id}/recipe")
    public ResponseEntity<Object> updatePropFc(@PathVariable Long id, @RequestBody FoodCategoryEntity fc) {
        return iFoodCategoryService.updateFoodCategory(id, fc);
    }


//    @GetMapping
//    public List<FoodCategoryEntity> fc() {
//        return foodCategoryRepository.findAll();
//    }
}
