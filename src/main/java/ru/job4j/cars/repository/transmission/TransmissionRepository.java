package ru.job4j.cars.repository.transmission;

import ru.job4j.cars.model.Transmission;

import java.util.Collection;
import java.util.Optional;

public interface TransmissionRepository {

    Optional<Transmission> findById(int id);

    Collection<Transmission> findAll();

}
