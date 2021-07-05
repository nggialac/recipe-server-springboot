package com.lacnguyen.recipeserver.repository;

import com.lacnguyen.recipeserver.entity.TipsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipsRepository extends JpaRepository<TipsEntity, Long> {
    Page<TipsEntity> findAllByTitleContains(String name, Pageable pageable);
}
