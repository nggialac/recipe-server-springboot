package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.repository.CourseRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.lang.Long;
import java.util.Optional;

@Api(value = "Course APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseApi {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ICourseService iCourseService;

    @GetMapping
    public Collection<CourseEntity> findListRecipe() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseEntity> findRecipeById(@PathVariable("id") Long id) {
        return iCourseService.findById(id);
    }

    @PostMapping
    public CourseEntity createRecipeEntity(@RequestBody CourseEntity objCourse) {
        return courseRepository.save(objCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeEntity(@PathVariable("id") Long id) {
        courseRepository.deleteById(id);
    }

}
