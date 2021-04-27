package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAll();

    List<CourseEntity> findByCourseId(Long id);

    CourseEntity save(CourseEntity objCourse);

    CourseEntity deleteByCourseId(Long id);
}
