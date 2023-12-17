package ru.job4j.cars.service;

import ru.job4j.cars.model.Photo;

import java.util.Collection;
import java.util.Optional;

public interface PhotoService {

    Optional<Photo> add(Photo photo);

    Optional<Photo> update(Photo photo);

    boolean deleteById(int id);

    Optional<Photo> findById(int id);

    Collection<Photo> findAll();

}
