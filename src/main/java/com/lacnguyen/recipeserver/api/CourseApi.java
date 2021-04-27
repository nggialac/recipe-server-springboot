package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.repository.CourseRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

@Api(value = "Course APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseApi {

    @Autowired
    private ICourseService iCourseService;

    @GetMapping
    public Collection<CourseEntity> findListCourse() {
        return iCourseService.findAll();
    }

    @GetMapping("/{id}")
    public List<CourseEntity> findCourseById(@PathVariable("id") Long id) {
        return iCourseService.findByCourseId(id);
    }

    @PostMapping
    public ResponseEntity<CourseEntity> createRecipeEntity(@RequestBody CourseEntity objCourse) {
        return new ResponseEntity<>(iCourseService.save(objCourse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeEntity(@PathVariable("id") Long id) {
        iCourseService.deleteByCourseId(id);
    }

}
