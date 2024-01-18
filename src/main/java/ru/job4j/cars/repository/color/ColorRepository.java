package ru.job4j.cars.repository.color;

import ru.job4j.cars.model.Color;

import java.util.Collection;
import java.util.Optional;

public interface ColorRepository {

    Optional<Color> findById(int id);

    Collection<Color> findAll();

}
