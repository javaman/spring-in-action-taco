package xyz.javaman.taco.entities;

import lombok.Data;

@Data
public class IngredientRef {

    private String ingredient;

    private Long taco;

    private Integer tacoKey;
}
