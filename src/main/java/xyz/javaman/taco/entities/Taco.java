package xyz.javaman.taco.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    private Long tacoOrder;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients;
}
