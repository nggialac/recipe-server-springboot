package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.TipsEntity;
import com.lacnguyen.recipeserver.repository.TipsRepository;
import com.lacnguyen.recipeserver.service.ITipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TipsService implements ITipsService {

    @Autowired
    TipsRepository tipsRepository;

    @Override
    public List<TipsEntity> findTipsAll() {
        return tipsRepository.findAll();
    }

    @Override
    public Page<TipsEntity> findTipsAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipsRepository.findAll(pageable);
    }

    @Override
    public Optional<TipsEntity> findTipsById(Long id) {
        return tipsRepository.findById(id);
    }

    @Override
    public TipsEntity save(TipsEntity tipsEntity){
        return tipsRepository.save(tipsEntity);
    }

    @Override
    public boolean isExist(Long id){
        return tipsRepository.existsById(id);
    }

    @Override
    public Page<TipsEntity> findTipsTitle(String name, int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipsRepository.findAllByTitleContains(name, pageable);
    }
}
