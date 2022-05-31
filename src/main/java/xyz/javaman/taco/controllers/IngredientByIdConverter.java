package xyz.javaman.taco.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.entities.IngredientRef;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, IngredientRef> {

    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientRef convert(String source) {
        IngredientRef result = new IngredientRef();
        result.setIngredient(source);
        return result;
    }
}
