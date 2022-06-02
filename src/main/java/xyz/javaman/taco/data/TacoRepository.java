package xyz.javaman.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.javaman.taco.entities.Taco;

import java.util.UUID;

@Repository
public interface TacoRepository extends CrudRepository<Taco, UUID> {
}
