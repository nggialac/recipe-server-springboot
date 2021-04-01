package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @ManyToMany(mappedBy = "foodCategories")
    private List<RecipeEntity> recipe = new ArrayList<>();
}