package ru.job4j.cars.repository.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@AllArgsConstructor
@Repository
public class HibernateUserRepository implements UserRepository {

    private final static Logger LOGGER = Logger.getLogger(HibernateUserRepository.class.getName());
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public Optional<User> add(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            LOGGER.info(e.toString());
        }
        return Optional.empty();

    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public Optional<User> update(User user) {
        crudRepository.run(session -> session.merge(user));
        return Optional.of(user);
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     * @return результат удаления
     */
    @Override
    public boolean delete(int userId) {
        int res = crudRepository.runWithConfirm(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
        return res != 0;
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("from User order by id asc", User.class);
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User u where u.id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User where login = :fLogin AND password = :fPassword", User.class,
                Map.of(
                        "fLogin", login,
                        "fPassword", password
                )
        );
    }
}