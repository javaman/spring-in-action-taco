package xyz.javaman.taco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.ArrayList;
import java.util.List;

@Data
@UserDefinedType("taco")
@AllArgsConstructor
@NoArgsConstructor
public class TacoUDT {

    private String name;

    private List<IngredientUDT> ingredients = new ArrayList<>();
}
