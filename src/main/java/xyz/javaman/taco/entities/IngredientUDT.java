package xyz.javaman.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType("ingredient")
@AllArgsConstructor
@NoArgsConstructor
public class IngredientUDT {

    private String name;

    private Ingredient.Type type;
}
