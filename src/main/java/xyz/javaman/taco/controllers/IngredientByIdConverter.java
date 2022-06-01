package xyz.javaman.taco.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.entities.Ingredient;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findById(source).orElse(null);
    }
}
