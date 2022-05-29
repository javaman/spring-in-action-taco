package xyz.javaman.taco.data.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.support.PropertiesBasedNamedQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.entities.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> result =
                jdbcTemplate.query("select id, name, type from Ingredient where id = ?", this::mapRowToIngredient, id);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}
