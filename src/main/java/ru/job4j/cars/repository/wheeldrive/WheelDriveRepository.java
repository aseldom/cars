package ru.job4j.cars.repository.wheeldrive;

import ru.job4j.cars.model.WheelDrive;

import java.util.Collection;
import java.util.Optional;

public interface WheelDriveRepository {

    Optional<WheelDrive> findById(int id);

    Collection<WheelDrive> findAll();

}
