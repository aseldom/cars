package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBody;
import ru.job4j.cars.repository.HibernateCarBodyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCarBodyService implements CarBodyService {

    private final HibernateCarBodyRepository hibernateCarBodyRepository;

    @Override
    public Optional<CarBody> findById(int id) {
        return hibernateCarBodyRepository.findById(id);
    }

    @Override
    public Collection<CarBody> findAll() {
        return hibernateCarBodyRepository.findAll();
    }
}
