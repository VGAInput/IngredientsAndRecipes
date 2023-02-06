package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Ingredient;
import com.example.ingredientsandrecipes.models.Recipe;

import java.util.Map;
import java.util.TreeMap;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);


    Recipe getRecipeByID(int id);

    TreeMap<Integer, Recipe> getAllRecipes();

    void upLoadList();

    Recipe updateRecipe(int id, Recipe recipe);

    void deleteRecipeById(int id);

    void saveToFile();

    void readFromFile();
}
