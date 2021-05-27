package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAll();

    Optional<CourseEntity> findByCourseId(Long id);

    CourseEntity save(CourseEntity objCourse);

    void deleteByCourseId(Long id);

    List<CourseEntity> findByCourseNameContains(String name);
}
