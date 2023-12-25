package ru.job4j.cars.service;

import ru.job4j.cars.dto.PhotoDto;
import ru.job4j.cars.dto.PostCreateDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.Collection;
import java.util.Optional;

public interface PostService {

    Optional<Post> add(PostCreateDto postCreateDto, PhotoDto photoDto, User user);

    Optional<Post> update(Post post);

    boolean deleteById(int id);

    Optional<Post> findById(int id);

    Collection<PostDto> findAll();

    Collection<Post> findLastDay();

    Collection<Post> findWithPhoto();

}
