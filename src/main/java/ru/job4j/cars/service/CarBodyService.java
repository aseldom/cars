package ru.job4j.cars.service;

import ru.job4j.cars.model.CarBody;

import java.util.Collection;
import java.util.Optional;

public interface CarBodyService {

    Optional<CarBody> findById(int id);

    Collection<CarBody> findAll();

}
