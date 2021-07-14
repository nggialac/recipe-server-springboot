package com.lacnguyen.recipeserver.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacnguyen.recipeserver.entity.LoginEntity;
import com.lacnguyen.recipeserver.entity.TipsEntity;
import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.models.JwtResponse;
import com.lacnguyen.recipeserver.models.ResourceNotFoundException;
import com.lacnguyen.recipeserver.service.IUserService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.apache.tomcat.util.codec.binary.Base64;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Api(value = "USER APIs")
@CrossOrigin
@RestController
@RequestMapping("/login")
public class UserApi {

    @Autowired
    IUserService iUserService;

//    @PostMapping
//    public ResponseEntity<UserEntity> userLogin(@RequestBody String json) throws JsonProcessingException {
//        //System.out.println(json);
//        ObjectMapper mapper = new ObjectMapper();
//        LoginEntity loginEntity = mapper.readValue(json, LoginEntity.class);
//        return new ResponseEntity<>(iUserService.userLogin(loginEntity.getUsername(), loginEntity.getPassword()), HttpStatus.OK);
//    }

//    @GetMapping("/user/{id}")
//    public UserEntity getInfoUser(@PathVariable("id") Long id) {
//        return iUserService.getInfo(id);
//    }

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        UserEntity temp = iUserService.findUserByName(userEntity.getUserName());
        if(temp != null){
            return new ResponseEntity<>(temp,HttpStatus.NOT_ACCEPTABLE);
        }
        else return new ResponseEntity<>(iUserService.save(userEntity), HttpStatus.OK);
    }

    @PostMapping("user/{id}")
    public UserEntity userChangePass(@PathVariable("id") Long id,
                                      @RequestParam("oldpass") String oldPass,
                                      @RequestParam("newpass") String newPass) {
        if (!iUserService.isExist(id)) {
            throw new ResourceNotFoundException("Not found");
        }
        return iUserService.getUserById(id).map(user -> {
            if(user.getPassword().equals(oldPass)){
                user.setPassword(newPass);
                return iUserService.save(user);
            }
            throw new ResourceNotFoundException("Password not correct!");
        }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @PostMapping
    public ResponseEntity<LoginEntity> userLogin(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        LoginEntity loginEntity = mapper.readValue(json, LoginEntity.class);
        UserEntity userEntity = iUserService.userLogin(loginEntity.getUsername(), loginEntity.getPassword());
        if (userEntity != null) {
            String token = getJWTToken(userEntity.getUserName());
            LoginEntity user = new LoginEntity();
            user.setUsername(userEntity.getUserName());
//            user.setFullname(userEntity.getFullName());
            user.setToken(token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("testJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

//    @PostMapping("/decodeJWT")
//    private ResponseEntity<UserEntity> getDecodeJWT(@RequestBody String token) throws IOException, JSONException {
//        String payload = token.split("\\.")[1];
//        JSONObject obj = new JSONObject(new String(Base64.decodeBase64(payload), "UTF-8"));
//        String subject = obj.getString("sub");
//        UserEntity userEntity = iUserService.getInfo(subject);
//        if(userEntity != null) return new ResponseEntity<>(userEntity, HttpStatus.OK);
//        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping("/decodejwt")
    private ResponseEntity<UserEntity> getUserFromDecodeJWT(@RequestHeader("Authorization") String token) throws IOException, JSONException {
        if (token == null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        String subToken = token.substring(7, token.length());
        System.out.println(subToken);
        String payload = subToken.split("\\.")[1];
        JSONObject obj = new JSONObject(new String(Base64.decodeBase64(payload), "UTF-8"));
        String subject = obj.getString("sub");
        UserEntity userEntity = iUserService.getInfo(subject);
        if (userEntity != null) return new ResponseEntity<>(userEntity, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
