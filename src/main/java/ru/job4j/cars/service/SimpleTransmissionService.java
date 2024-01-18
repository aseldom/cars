package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Transmission;
import ru.job4j.cars.repository.transmission.HibernateTransmissionRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTransmissionService implements TransmissionService {

    private final HibernateTransmissionRepository hibernateTransmissionRepository;

    @Override
    public Optional<Transmission> findById(int id) {
        return hibernateTransmissionRepository.findById(id);
    }

    @Override
    public Collection<Transmission> findAll() {
        return hibernateTransmissionRepository.findAll();
    }

}
