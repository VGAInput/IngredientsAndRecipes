package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Ingredient;

import java.util.TreeMap;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    void upLoadList();

    Ingredient updateIngredient(int id, Ingredient ingredient);

    Ingredient getIngredientByID(int id);

    TreeMap<Integer, Ingredient> getAllIngredients();

    void deleteIngredientById(int id);

    void saveToFile();

    void readFromFile();
}
