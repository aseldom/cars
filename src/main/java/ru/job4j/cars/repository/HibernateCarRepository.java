package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateCarRepository implements CarRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Car> add(Car car) {
        crudRepository.run(session -> session.persist(car));
        return Optional.of(car);
    }

    @Override
    public Optional<Car> update(Car car) {
        crudRepository.run(session -> session.merge(car));
        return Optional.of(car);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE FROM Car WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional("FROM Car c LEFT JOIN FETCH c.historyOwners WHERE c.id = :fId",
                Car.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Car> findAll() {
        return crudRepository.query("FROM Car", Car.class);
    }

}
