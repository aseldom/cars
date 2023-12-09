package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarRepository {

    Optional<Car> add(Car car);

    Optional<Car> update(Car car);

    boolean deleteById(int id);

    Optional<Car> findById(int id);

    Collection<Car> findAll();

}
