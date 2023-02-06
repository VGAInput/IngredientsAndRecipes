package com.example.ingredientsandrecipes.controllers;

import com.example.ingredientsandrecipes.models.Recipe;
import com.example.ingredientsandrecipes.services.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Контроллёр рецептов", description = "CRUD-операции для работы с рецептами. ")

public class RecipesController {
    private RecipeServiceImpl recipeService;

    public RecipesController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового рецепта.")
    public ResponseEntity addNewIngredient(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(" Recipe ID: " + recipeService.createRecipe(recipe));
    }
    @GetMapping("/{id}")
    @Operation(description = "Получение рецепта по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })

    public ResponseEntity<Recipe> getRecipeById(@RequestParam int id) {
        Recipe recipe = recipeService.getRecipeByID(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/download")
    @Operation(description = "Получение всех рецептов на жёсткий диск.")
    public void getFile(){
        recipeService.upLoadList();
    }

    @GetMapping("/all")
    @Operation(description = "Получение всех рецептов.")
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление рецепта по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok("Рецепт №" + id + " удалён");
    }

    @PutMapping("/edit/{id}")
    @Operation(description = "Редактирование рецепта по ID.")

    public Recipe putRecipe(@RequestBody Recipe recipe,@PathVariable int id) {
        Recipe updateRecipe = recipeService.updateRecipe(id, recipe);
        return recipe;
    }


}
