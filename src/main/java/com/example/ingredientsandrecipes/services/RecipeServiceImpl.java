package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TreeMap;

@Service
@Qualifier("recipeFileService")

public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private final FileService recipeFileService;
    private int generateId = 0;
    private static TreeMap<Integer, Recipe> recipes = new TreeMap<>();


    public RecipeServiceImpl(@Qualifier("recipeFileService") FileService recipeFileService) {
        this.recipeFileService = recipeFileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(generateId, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(int id) {
        return recipes.get(id);
    }

    @Override
    public TreeMap<Integer, Recipe> getAllRecipes() {

        return recipes;
    }

    @Override
    public void upLoadList() {
        saveToFile();
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        recipes.put(id, recipe);
        return recipe;
    }

    @Override
    public void deleteRecipeById(int id) {
        recipes.remove(id);
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = recipeFileService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
