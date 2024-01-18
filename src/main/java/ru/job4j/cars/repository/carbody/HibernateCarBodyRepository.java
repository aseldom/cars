package ru.job4j.cars.repository.carbody;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateCarBodyRepository implements CarBodyRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<CarBody> findById(int id) {
        return crudRepository.optional("FROM CarBody c WHERE c.id = :fId",
                CarBody.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<CarBody> findAll() {
        return crudRepository.query("FROM CarBody", CarBody.class);
    }

}
