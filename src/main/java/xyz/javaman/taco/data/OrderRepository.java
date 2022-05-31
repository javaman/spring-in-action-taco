package xyz.javaman.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.javaman.taco.entities.TacoOrder;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

}
