package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HibernateEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Engine> add(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return Optional.of(engine);
    }

    @Override
    public Optional<Engine> update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
        return Optional.of(engine);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE from Engine WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional("FROM Engine e WHERE e.id = :fId",
                Engine.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }

}
