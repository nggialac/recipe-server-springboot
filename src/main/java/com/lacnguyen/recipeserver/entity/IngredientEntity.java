package com.lacnguyen.recipeserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient")
public class IngredientEntity {

    @Id
    @Column(name = "ingredientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;

    @Column(name = "ingredientname")
    private String ingredientName;

    @Column(name = "ingredientquantity")
    private float ingredientQuantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private MeasurementEntity measurement;
}
