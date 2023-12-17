package ru.job4j.cars.service;

import ru.job4j.cars.model.Color;

import java.util.Collection;
import java.util.Optional;

public interface ColorService {

    Optional<Color> findById(int id);

    Collection<Color> findAll();

}
