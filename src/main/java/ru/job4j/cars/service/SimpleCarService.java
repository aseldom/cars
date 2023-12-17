package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.HibernateCarRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCarService implements CarService {

    private final HibernateCarRepository hibernateCarRepository;

    @Override
    public Optional<Car> add(Car car) {
        return hibernateCarRepository.add(car);
    }

    @Override
    public Optional<Car> update(Car car) {
        return hibernateCarRepository.update(car);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernateCarRepository.deleteById(id);
    }

    @Override
    public Optional<Car> findById(int id) {
        return hibernateCarRepository.findById(id);
    }

    @Override
    public Collection<Car> findAll() {
        return hibernateCarRepository.findAll();
    }
}
