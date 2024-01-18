package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.EngineType;
import ru.job4j.cars.repository.enginetype.HibernateEngineTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineTypeService implements EngineTypeService {

    private final HibernateEngineTypeRepository hibernateEngineTypeRepository;

    @Override
    public Optional<EngineType> findById(int id) {
        return hibernateEngineTypeRepository.findById(id);
    }

    @Override
    public Collection<EngineType> findAll() {
        return hibernateEngineTypeRepository.findAll();
    }
}
