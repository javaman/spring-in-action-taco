package xyz.javaman.taco.data;

import xyz.javaman.taco.entities.Ingredient;

import java.util.Collection;
import java.util.Optional;

public interface IngredientRepository {

    Collection<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
