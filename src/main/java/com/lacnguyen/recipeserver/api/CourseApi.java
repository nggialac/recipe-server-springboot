package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import com.lacnguyen.recipeserver.entity.RecipeEntity;
import com.lacnguyen.recipeserver.repository.CourseRepository;
import com.lacnguyen.recipeserver.service.ICourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Collection<CourseEntity>> findListCourse() {
        return new ResponseEntity<>(iCourseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> findCourseById(@PathVariable("id") Long id) {
        Optional<CourseEntity> courseOptional = iCourseService.findByCourseId(id);
        return courseOptional.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CourseEntity> createRecipeEntity(@RequestBody CourseEntity objCourse) {
        return new ResponseEntity<>(iCourseService.save(objCourse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseEntity> deleteRecipeEntity(@PathVariable("id") Long id) {
        //iCourseService.deleteByCourseId(id);
        Optional<CourseEntity> courseOptional = iCourseService.findByCourseId(id);
        return courseOptional.map(course -> {
            iCourseService.deleteByCourseId(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/coursename")
    public ResponseEntity<List<CourseEntity>> findCourseByName(@RequestParam("name") String name) {
        return new ResponseEntity<>(iCourseService.findByCourseNameContains(name.trim()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable("id") long id, @RequestBody CourseEntity course) {
        Optional<CourseEntity> courseData = iCourseService.findByCourseId(id);

        if (courseData.isPresent()) {
            CourseEntity _course = courseData.get();
            _course.setCourseName(course.getCourseName());
            _course.setCreateBy(course.getCreateBy());
            _course.setCreateDate(course.getCreateDate());
            _course.setCourseContent(course.getCourseContent());
            _course.setCourseImage(course.getCourseImage());
            return new ResponseEntity<>(iCourseService.save(_course), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
