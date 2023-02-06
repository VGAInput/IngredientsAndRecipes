package com.example.ingredientsandrecipes;

import com.example.ingredientsandrecipes.services.FileService;
import com.example.ingredientsandrecipes.services.IngredientFileServiceImpl;
import com.example.ingredientsandrecipes.services.RecipeFileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean (name = "ingredientFileService")
    public FileService ingredientFileService(){
        return new IngredientFileServiceImpl();
    }
    @Bean (name = "recipeFileService")
    public FileService recipeFileService(){
        return new RecipeFileServiceImpl();
    }
}
