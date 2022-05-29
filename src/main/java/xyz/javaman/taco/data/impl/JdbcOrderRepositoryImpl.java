package xyz.javaman.taco.data.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.javaman.taco.data.OrderRepository;
import xyz.javaman.taco.entities.Ingredient;
import xyz.javaman.taco.entities.IngredientRef;
import xyz.javaman.taco.entities.Taco;
import xyz.javaman.taco.entities.TacoOrder;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepositoryImpl implements OrderRepository {

    private final JdbcOperations jdbcOperations;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO TACO_ORDER (delivery_name, " +
                        "delivery_street, delivery_city, delivery_state, " +
                        "delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(LocalDateTime.now());
        PreparedStatementCreator psc =
            pscf.newPreparedStatementCreator(
                    Arrays.asList(order.getDeliveryName(),
                            order.getDeliveryStreet(), order.getDeliveryCity(), order.getDeliveryState(),
                            order.getDeliveryZip(), order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(),
                            order.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }

    private long saveTaco(long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco (name, created_at, taco_order, taco_order_key) values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT
        );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(Arrays.asList(
                        taco.getName(), taco.getCreatedAt(), orderId, orderKey
                ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update("insert into Ingredient_Ref(ingredient, taco, taco_key) values (?, ?, ?)",
                    ingredient.getId(), tacoId, key++);
        }
    }
}
