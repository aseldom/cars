package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.EngineType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateEngineTypeRepository implements EngineTypeRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<EngineType> findById(int id) {
        return crudRepository.optional("FROM EngineType c WHERE c.id = :fId",
                EngineType.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<EngineType> findAll() {
        return crudRepository.query("FROM EngineType", EngineType.class);
    }

}
