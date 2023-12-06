package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
@Repository
public class HibernatePostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Post> getLastDay() {
        Timestamp nowMinusDay = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        return crudRepository.query("FROM Post p WHERE p.startTime >= :fNowMinusDay",
                Post.class,
                Map.of("fNowMinusDay", nowMinusDay));
    }

    @Override
    public Collection<Post> getWithPhoto() {
        return crudRepository.query(
                "FROM Post p WHERE exists elements(p.photos)",
                Post.class
        );
    }

    @Override
    public Collection<Car> getByModel(String model) {
        return crudRepository.query("FROM Post p WHERE p.car.name = :fModel",
                Car.class,
                Map.of("fModel", model));
    }
}
