package xyz.javaman.taco.controllers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.javaman.taco.entities.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    @Override
    public Ingredient convert(String source) {
        return Ingredient
                .getAllIngredients()
                .stream()
                .filter(ingredient -> source.equals(ingredient.getId()))
                .findFirst().get();
    }
}
