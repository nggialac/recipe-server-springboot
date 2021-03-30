package com.lacnguyen.recipeserver.entity;

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

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "food_category_id")
    private FoodCategoryEntity foodCategory;

    @Column(name = "recipename")
    private String recipeName;

    @Column(name = "recipedescription")
    private String recipeDescription;

    @Column(name = "preptime")
    private int prepTime;

    @Column(name = "cooktime")
    private int cookTime;

    @OneToMany(mappedBy = "recipe")
    private Collection<RecipeStepEntity> recipeSteps = new ArrayList<>();
}
