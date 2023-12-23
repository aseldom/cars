package ru.job4j.cars.repository;

import ru.job4j.cars.model.Photo;

import java.util.Collection;
import java.util.Optional;

public interface PhotoRepository {

    Optional<Photo> add(Photo photo);

    boolean deleteById(int id);

    Optional<Photo> findById(int id);

    Collection<Photo> findAll();

}
