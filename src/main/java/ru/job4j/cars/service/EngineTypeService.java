package ru.job4j.cars.service;

import ru.job4j.cars.model.EngineType;

import java.util.Collection;
import java.util.Optional;

public interface EngineTypeService {

    Optional<EngineType> findById(int id);

    Collection<EngineType> findAll();

}
