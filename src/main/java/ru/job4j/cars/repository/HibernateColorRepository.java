package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Color;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateColorRepository implements ColorRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Color> findById(int id) {
        return crudRepository.optional("FROM Color c WHERE c.id = :fId",
                Color.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Color> findAll() {
        return crudRepository.query("FROM Color", Color.class);
    }

}
