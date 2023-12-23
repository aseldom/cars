package ru.job4j.cars.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.PhotoDto;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.repository.HibernatePhotoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimplePhotoService implements PhotoService {

    private final HibernatePhotoRepository hibernatePhotoRepository;
    private final String storageDirectory;

    public SimplePhotoService(HibernatePhotoRepository hibernatePhotoRepository,
                             @Value("${file.directory}") String storageDirectory) {
        this.hibernatePhotoRepository = hibernatePhotoRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Photo> add(PhotoDto photoDto) {
        String path = getNewFilePath(photoDto.getName());
        writeFileBytes(path, photoDto.getContent());
        return hibernatePhotoRepository.add(new Photo(photoDto.getName(), path));
    }

    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PhotoDto> getPhotoById(int id) {
        Optional<Photo> photo = hibernatePhotoRepository.findById(id);
        if (photo.isEmpty()) {
            return Optional.empty();
        }
        byte[] content = readPhotoAsByte(photo.get().getPath());
        return Optional.of(new PhotoDto(photo.get().getName(), content));

    }

    private byte[] readPhotoAsByte(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteById(int id) {
        boolean res = false;
        Optional<Photo> photoOptional = hibernatePhotoRepository.findById(id);
        if (photoOptional.isPresent()) {
            deleteFile(photoOptional.get().getPath());
            res = hibernatePhotoRepository.deleteById(id);
        }
        return res;
    }

    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
