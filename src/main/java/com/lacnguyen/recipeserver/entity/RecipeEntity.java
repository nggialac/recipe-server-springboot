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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeid")
    private Long recipeId;

    @Column(name = "recipename")
    private String recipeName;

    @Column(name = "recipedescription")
    private String recipeDescription;

    @Column(name = "preptime")
    private int prepTime;

    @Column(name = "cooktime")
    private int cookTime;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private Collection<RecipeStepEntity> recipe_steps = new ArrayList<>();


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private Collection<CourseEntity> courses = new ArrayList<>();


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private Collection<IngredientEntity> ingredients = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "food_category_id"))
    private List<FoodCategoryEntity> foodCategories = new ArrayList<>();
}