package com.lacnguyen.recipeserver.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacnguyen.recipeserver.entity.LoginEntity;
import com.lacnguyen.recipeserver.entity.UserEntity;
import com.lacnguyen.recipeserver.repository.UserRepository;
import com.lacnguyen.recipeserver.service.IUserService;
import com.lacnguyen.recipeserver.service.impl.UserService;

import io.swagger.annotations.Api;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "USER APIs")
@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class UserApi {

    @Autowired
    IUserService iUserService;

//    @PostMapping
//    public ResponseEntity<UserEntity> userLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
//        return new ResponseEntity<>(iUserService.userLogin(username, password), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<UserEntity> userLogin(@RequestBody String json) throws JsonProcessingException {
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        LoginEntity loginEntity = mapper.readValue(json, LoginEntity.class);
        return new ResponseEntity<>(iUserService.userLogin(loginEntity.getUsername(), loginEntity.getPassword()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public UserEntity getInfoUser(@RequestParam("id") Long id) {
        return iUserService.getInfo(id);
    }

//    @PostMapping("user")
//    public UserEntity login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
//
//        String token = getJWTToken(username);
//        UserEntity user = new UserEntity();
//        user.setUserName(username);
//        user.setToken(token);
//        return user;
//
//    }
//
//    private String getJWTToken(String username) {
//        String secretKey = "mySecretKey";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//        return "Bearer " + token;
//    }


}
