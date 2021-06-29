package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_category")
public class FoodCategoryEntity {

    @Id
    @Column(name = "foodcategoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodCategoryId;

    @Column(name = "foodcategoryname")
    private String foodCategoryName;


    @JsonIgnore
//    @JsonIgnoreProperties(value="foodCategories")
    @ManyToMany(mappedBy = "foodCategories", cascade = CascadeType.ALL)
    private List<RecipeEntity> recipe;
}