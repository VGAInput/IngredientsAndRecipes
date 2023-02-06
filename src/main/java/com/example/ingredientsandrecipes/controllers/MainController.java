package com.example.ingredientsandrecipes.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@Tag(name = "Стартовая страница")

public class MainController {
    @GetMapping
    public String helloString(){
        return "Hello application.";
    }
}
