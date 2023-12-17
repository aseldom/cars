package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.HibernatePostRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {

    private final HibernatePostRepository hibernatePostRepository;

    @Override
    public Optional<Post> add(Post post) {
        return hibernatePostRepository.add(post);
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
    public Collection<Post> findAll() {
        return hibernatePostRepository.findAll();
    }

    @Override
    public Collection<Post> findLastDay() {
        return hibernatePostRepository.findLastDay();
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return hibernatePostRepository.findWithPhoto();
    }

}
