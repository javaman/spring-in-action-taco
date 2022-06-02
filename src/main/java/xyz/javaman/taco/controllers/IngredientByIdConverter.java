package xyz.javaman.taco.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.entities.Ingredient;
import xyz.javaman.taco.entities.IngredientUDT;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientUDT convert(String source) {
        return ingredientRepository
                .findById(source)
                .map(i -> new IngredientUDT(i.getName(), i.getType()))
                .orElse(null);
    }
}
