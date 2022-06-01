package xyz.javaman.taco.entities;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Taco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message = "You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();
}
