package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Color;
import ru.job4j.cars.repository.HibernateColorRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleColorService implements ColorService {

    private final HibernateColorRepository hibernateColorRepository;

    @Override
    public Optional<Color> findById(int id) {
        return hibernateColorRepository.findById(id);
    }

    @Override
    public Collection<Color> findAll() {
        return hibernateColorRepository.findAll();
    }
}
