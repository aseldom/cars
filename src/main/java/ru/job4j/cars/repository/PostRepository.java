package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {

    Optional<Post> add(Post post);

    Optional<Post> update(Post post);

    boolean deleteById(int id);

    Optional<Post> findById(int id);

    Collection<Post> findAll();

    Collection<Post> findLastDay();

    Collection<Post> findWithPhoto();

    Collection<Car> findByModel(String model);

}
