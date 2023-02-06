package com.example.ingredientsandrecipes.services;

import com.example.ingredientsandrecipes.models.Recipe;

import java.util.TreeMap;

public interface FileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanFile();
}
