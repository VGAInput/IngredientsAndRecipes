package com.example.ingredientsandrecipes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int cookingTimeMinutes;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cookingSteps;

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", cookingTimeMinutes=" + cookingTimeMinutes +
                ", ingredients=" + ingredients +
                ", cookingSteps=" + cookingSteps +
                '}';
    }
}
