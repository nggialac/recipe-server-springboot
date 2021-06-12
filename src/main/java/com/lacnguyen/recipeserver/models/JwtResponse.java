package com.lacnguyen.recipeserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    @JsonProperty("jti")
    public String jti;

    @JsonProperty("sub")
    public String subject;

    @JsonProperty("authorities")
    public List<String> authorities;

    @JsonProperty("iat")
    public String iat;

    @JsonProperty("exp")
    public String exp;
}

//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public static class authors{
//
//}
