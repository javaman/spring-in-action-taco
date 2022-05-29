package xyz.javaman.taco.data;

import xyz.javaman.taco.entities.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
