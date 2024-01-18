package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.WheelDrive;
import ru.job4j.cars.repository.wheeldrive.HibernateWheelDriveRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleWheelDriveService implements WheelDriveService {

    private final HibernateWheelDriveRepository hibernateWheelDriveRepository;

    @Override
    public Optional<WheelDrive> findById(int id) {
        return hibernateWheelDriveRepository.findById(id);
    }

    @Override
    public Collection<WheelDrive> findAll() {
        return hibernateWheelDriveRepository.findAll();
    }

}
