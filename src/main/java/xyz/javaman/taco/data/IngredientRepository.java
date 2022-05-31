package xyz.javaman.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.javaman.taco.entities.Ingredient;


@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
