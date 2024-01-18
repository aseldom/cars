package ru.job4j.cars.repository.engine;

import ru.job4j.cars.model.Engine;

import java.util.Collection;
import java.util.Optional;

public interface EngineRepository {

    Optional<Engine> add(Engine engine);

    Optional<Engine> update(Engine engine);

    boolean deleteById(int id);

    Optional<Engine> findById(int id);

    Collection<Engine> findAll();

}
