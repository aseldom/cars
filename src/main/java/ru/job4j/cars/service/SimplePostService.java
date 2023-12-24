package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.PhotoDto;
import ru.job4j.cars.dto.PostCreateDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.mapper.PostMapper;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {

    private final HibernatePostRepository hibernatePostRepository;
    private final HibernateCarRepository hibernateCarRepository;
    private final HibernateColorRepository hibernateColorRepository;
    private final HibernateCarBodyRepository hibernateCarBodyRepository;
    private final HibernateEngineTypeRepository hibernateEngineTypeRepository;
    private final HibernateWheelDriveRepository hibernateWheelDriveRepository;
    private final HibernateTransmissionRepository hibernateTransmissionRepository;
    private final SimplePhotoService simplePhotoService;
    private final PostMapper postMapper;

    @Override
    public Optional<Post> add(PostCreateDto postCreateDto, PhotoDto photoDto, User user) {
        Car car = saveCar(user, postCreateDto).get();
        Post post = postMapper.getPostFromPostCreateDto(postCreateDto);
        post.setCar(car);
        post.setUser(user);
        post.getPriceHistories().add(getPriceHistory(postCreateDto));
        Optional<Photo> photo = simplePhotoService.add(photoDto);
        photo.ifPresent(value -> post.getPhotos().add(value));
        return hibernatePostRepository.add(post);
    }

    private PriceHistory getPriceHistory(PostCreateDto postCreateDto) {
        long price = postCreateDto.getCarPrice();
        return new PriceHistory(price, price);
    }

    @Override
    public Optional<Post> update(Post post) {
        return hibernatePostRepository.update(post);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernatePostRepository.deleteById(id);
    }

    @Override
    public Optional<Post> findById(int id) {
        return hibernatePostRepository.findById(id);
    }

    @Override
    public Collection<PostDto> findAll() {
        return hibernatePostRepository
                .findAll()
                .stream()
                .map(postMapper::getPostDto)
                .toList();
    }

    @Override
    public Collection<Post> findLastDay() {
        return hibernatePostRepository.findLastDay();
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return hibernatePostRepository.findWithPhoto();
    }

    private Optional<Car> saveCar(User user, PostCreateDto postCreateDto) {
        Car car = postMapper.getCarFromPostCreateDto(postCreateDto);
        car.getEngine().setEngineType(hibernateEngineTypeRepository.findById(postCreateDto.getCarEngineId()).get());
        car.setColor(hibernateColorRepository.findById(postCreateDto.getCarColorId()).get());
        car.setCarBody(hibernateCarBodyRepository.findById(postCreateDto.getCarBodyId()).get());
        car.setWheelDrive(hibernateWheelDriveRepository.findById(postCreateDto.getCarWheelDriveId()).get());
        car.setTransmission(hibernateTransmissionRepository.findById(postCreateDto.getCarTransmissionId()).get());
        HistoryOwner historyOwner = postMapper.getHistoryOwnerFromPostCreateDto(postCreateDto, new Owner(user));
        car.getHistoryOwners().add(historyOwner);
        return hibernateCarRepository.add(car);
    }

}
