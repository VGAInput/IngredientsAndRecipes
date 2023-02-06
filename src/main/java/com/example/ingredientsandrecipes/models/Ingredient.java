package com.example.ingredientsandrecipes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int amount;
    private String unitOfMeasure;

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';
    }

}
