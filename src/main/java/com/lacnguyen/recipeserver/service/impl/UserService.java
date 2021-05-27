package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.repository.UserRepository;
import com.lacnguyen.recipeserver.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity userLogin(String username, String password) {
        return userRepository.findByUserNameAndAndPassword(username, password);
    }

    @Override
    public UserEntity getInfo(Long id) {
        return userRepository.findByUserId(id);
    }
}
