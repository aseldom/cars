package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.repository.HibernatePhotoRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePhotoService implements PhotoService {

    private final HibernatePhotoRepository hibernatePhotoRepository;

    @Override
    public Optional<Photo> add(Photo photo) {
        return hibernatePhotoRepository.add(photo);
    }

    @Override
    public Optional<Photo> update(Photo photo) {
        return hibernatePhotoRepository.update(photo);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernatePhotoRepository.deleteById(id);
    }

    @Override
    public Optional<Photo> findById(int id) {
        return hibernatePhotoRepository.findById(id);
    }

    @Override
    public Collection<Photo> findAll() {
        return hibernatePhotoRepository.findAll();
    }

}
