package ru.job4j.cars.repository.enginetype;

import ru.job4j.cars.model.EngineType;

import java.util.Collection;
import java.util.Optional;

public interface EngineTypeRepository {

    Optional<EngineType> findById(int id);

    Collection<EngineType> findAll();

}
