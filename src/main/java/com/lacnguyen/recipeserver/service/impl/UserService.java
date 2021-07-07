package com.lacnguyen.recipeserver.service.impl;

import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.repository.UserRepository;
import com.lacnguyen.recipeserver.service.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity findUserByName(String name){
        return userRepository.findByUserName(name);
    }


    @Override
    public UserEntity userLogin(String username, String password) {
        return userRepository.findByUserNameAndAndPassword(username, password);
    }

    @Override
    public boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getInfo(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public UserEntity getInfo(String username) {
        return userRepository.findByUserName(username);
    }
}
