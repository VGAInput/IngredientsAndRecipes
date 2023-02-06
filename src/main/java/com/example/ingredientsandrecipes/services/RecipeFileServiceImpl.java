package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeMap;

@Service
public class RecipeFileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String recipeFilePath;
    @Value("&{name.of.data.file.recipes}")
    private String recipeFileName;
@Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            System.out.println("saved");
            Files.writeString(Path.of(recipeFilePath,"/Recipes.json"), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipeFilePath,"/Recipes.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

@Override
    public boolean cleanFile() {

        try {
            Path path = Path.of(recipeFilePath + "/Recipes.json");
            Files.deleteIfExists(path);
            Files.createFile(path);

            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }



}
