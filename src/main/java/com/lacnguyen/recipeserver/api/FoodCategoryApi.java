package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.FoodCategoryEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.FoodCategoryRepository;
import com.lacnguyen.recipeserver.service.IFoodCategoryService;
import com.lacnguyen.recipeserver.service.impl.FoodCategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Food Category APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/foodcategory")
public class FoodCategoryApi {

    @Autowired
    FoodCategoryRepository ffoodCategoryRepository;

    @Autowired
    IFoodCategoryService iFoodCategoryService;

    @PostMapping("/create")
    public ResponseEntity<FoodCategoryEntity> createSingleFc(@RequestBody FoodCategoryEntity fc) {
        return new ResponseEntity<>(iFoodCategoryService.createFc(fc), HttpStatus.OK);
    }

    //Need to fix if FC existed
    @PostMapping("/create/recipe")
    public ResponseEntity<Object> createFC(@RequestBody FoodCategoryEntity fc) {
        return iFoodCategoryService.addFoodCategory(fc);
    }

    @DeleteMapping("/delete/{id}/recipe")
    public ResponseEntity<Object> deleteFC(@PathVariable Long id) {
        return iFoodCategoryService.deleteFoodCategory(id);
    }

    @GetMapping("/details/{id}/recipe")
    public FoodCategoryEntity getFC(@PathVariable Long id) {
        if (ffoodCategoryRepository.findById(id).isPresent())
            return ffoodCategoryRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/all/recipe")
    public List<FoodCategoryEntity> getAllFc() {
        return ffoodCategoryRepository.findAll();
    }

    @GetMapping("/all/recipe/pagination")
    public ResponseEntity<Map<String, Object>> getAllFcPageable(@RequestParam(required = false) String name, @RequestParam(value = "pageNumber") int pageNumber,
                                                                @RequestParam(value = "pageSize") int pageSize) {
        try {
            List<FoodCategoryEntity> fcList = new ArrayList<>();
            Page<FoodCategoryEntity> fcPage;
//            if (name == null)
            fcPage = iFoodCategoryService.findListFc_Paginate(pageNumber, pageSize);
//            else
//                fcPage = iRecipeService.findByRecipeNameContains(name, pageNumber, pageSize);

            fcList = fcPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("fcList", fcList);
            response.put("currentPage", fcPage.getNumber());
            response.put("totalItems", fcPage.getTotalElements());
            response.put("totalPages", fcPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
