package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.TreeMap;

@Service
@Qualifier("ingredientFileService")
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    final private FileService ingredientFileService;
    private int generateId = 0;
    private static TreeMap<Integer, Ingredient> ingredients = new TreeMap<>();

    public IngredientServiceImpl(FileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredients.put(generateId, ingredient);
        generateId++;
        return ingredient;
    }

    @Override
    public void upLoadList() {
        saveToFile();
    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        ingredients.put(id, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientByID(int id) {
        return ingredients.get(id);
    }

    @Override
    public TreeMap<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }

    @Override
    public void deleteIngredientById(int id) {
        ingredients.remove(id);
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = ingredientFileService.readFromFile();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
