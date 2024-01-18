package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.engine.HibernateEngineRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineService implements EngineService {

    private final HibernateEngineRepository hibernateEngineRepository;

    @Override
    public Optional<Engine> add(Engine engine) {
        return hibernateEngineRepository.add(engine);
    }

    @Override
    public Optional<Engine> update(Engine engine) {
        return hibernateEngineRepository.update(engine);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernateEngineRepository.deleteById(id);
    }

    @Override
    public Optional<Engine> findById(int id) {
        return hibernateEngineRepository.findById(id);
    }

    @Override
    public Collection<Engine> findAll() {
        return hibernateEngineRepository.findAll();
    }

}
