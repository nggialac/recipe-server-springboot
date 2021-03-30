package com.lacnguyen.recipeserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quantity")
public class QuantityEntity {

    @Id
    @Column(name = "quantityid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantityId;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipeId;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private IngredientEntity ingredient;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private MeasurementEntity measurement;

    @Column(name = "ingredientquantity")
    private float ingredientQuantity;
}
