package ru.job4j.cars.service;

import ru.job4j.cars.dto.PhotoDto;
import ru.job4j.cars.model.Photo;

import java.util.Optional;

public interface PhotoService {

    Optional<Photo> add(PhotoDto photoDto);

    boolean deleteById(int id);

    Optional<PhotoDto> getPhotoById(int id);

}
