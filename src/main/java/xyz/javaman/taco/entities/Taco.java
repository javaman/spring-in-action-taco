package xyz.javaman.taco.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();
}
