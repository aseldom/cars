package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.HibernateUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final HibernateUserRepository hibernateUserRepository;

    @Override
    public Optional<User> add(User user) {
        return hibernateUserRepository.add(user);
    }

    @Override
    public Optional<User> update(User user) {
        return hibernateUserRepository.update(user);
    }

    @Override
    public boolean delete(int userId) {
        return hibernateUserRepository.delete(userId);
    }

    @Override
    public List<User> findAllOrderById() {
        return hibernateUserRepository.findAllOrderById();
    }

    @Override
    public Optional<User> findById(int userId) {
        return hibernateUserRepository.findById(userId);
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return hibernateUserRepository.findByLikeLogin(key);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return hibernateUserRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return hibernateUserRepository.findByLoginAndPassword(login, password);
    }

}
