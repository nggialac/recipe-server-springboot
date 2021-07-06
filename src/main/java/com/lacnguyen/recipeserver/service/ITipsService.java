package com.lacnguyen.recipeserver.service;

import com.lacnguyen.recipeserver.entity.TipsEntity;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ITipsService {

    List<TipsEntity> findTipsAll();

    Page<TipsEntity> findTipsAll(int pageNumber, int pageSize);

    Optional<TipsEntity> findTipsById(Long id);

    TipsEntity save(TipsEntity tipsEntity);

    boolean isExist(Long id);

    Page<TipsEntity> findTipsTitle(String name, int pageNumber, int pageSize);

    void deleteTipsById(Long id);
}
