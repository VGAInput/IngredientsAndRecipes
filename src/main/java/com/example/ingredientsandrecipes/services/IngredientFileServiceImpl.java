package com.example.ingredientsandrecipes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String ingredientFilePath;
    @Value("&{name.of.data.file.ingredients}")
    private String ingredientFileName;
@Override
    public boolean saveToFile(String json) {
        try {
            cleanFile();
            System.out.println("saved");
            Files.writeString(Path.of(ingredientFilePath,"/Ingredients.json"), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath+ "/Ingredients.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

@Override
    public boolean cleanFile() {

        try {
            Path path = Path.of(ingredientFilePath + "/Ingredients.json");
            Files.deleteIfExists(path);
            Files.createFile(path);

            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }



}
