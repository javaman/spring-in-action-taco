package xyz.javaman.taco.entities;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("INGREDIENT_REF")
public class IngredientRef {

    private String ingredient;

    private Long taco;

    private Integer tacoKey;
}
