package com.example.ingredientsandrecipes.controllers;

import com.example.ingredientsandrecipes.models.Ingredient;
import com.example.ingredientsandrecipes.services.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/ingredients")
@Tag(name = "Контроллёр ингредиентов", description = "CRUD-операции для работы с ингредиентами. ")

public class IngredientsController {
    private IngredientServiceImpl ingredientService;

    @Autowired
    public IngredientsController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового ингредиента.")
    public ResponseEntity addNewIngredient(@RequestBody Ingredient newIngredient) {
        return ResponseEntity.ok(" Ingredient ID: " + ingredientService.createIngredient(newIngredient));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение ингредиента по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })
    public ResponseEntity<Ingredient> getIngredient(@RequestParam int id) {
        Ingredient ingredient = ingredientService.getIngredientByID(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/all")
    @Operation(description = "Получение всех ингредиентов.")
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredient() {
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление ингредиента по ID.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1")
    })

    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.ok("Ingredient №" + id + " has been removed");
    }
    @PutMapping("/edit/{id}")
    @Operation(description = "Редактирование ингредиента по ID.")

    public Ingredient putIngredient(@RequestBody Ingredient ingredient,@PathVariable int id) {
        Ingredient updateIngredient = ingredientService.updateIngredient(id, ingredient);
        return ingredient;
    }

}
