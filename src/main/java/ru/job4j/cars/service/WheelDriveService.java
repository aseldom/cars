package ru.job4j.cars.service;

import ru.job4j.cars.model.WheelDrive;

import java.util.Collection;
import java.util.Optional;

public interface WheelDriveService {

    Optional<WheelDrive> findById(int id);

    Collection<WheelDrive> findAll();

}
