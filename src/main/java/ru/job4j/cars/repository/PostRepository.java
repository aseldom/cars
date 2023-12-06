package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.util.Collection;

public interface PostRepository {

    Collection<Post> getLastDay();

    Collection<Post> getWithPhoto();

    Collection<Car> getByModel(String model);

}
