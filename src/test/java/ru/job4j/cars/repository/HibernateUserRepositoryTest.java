package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HibernateUserRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HibernateUserRepository userRepository = new HibernateUserRepository(new CrudRepository(sf));

    @AfterEach
    public void clearRepository() {
        for (var e : userRepository.findAllOrderById()) {
            userRepository.delete(e.getId());
        }
    }

    private User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("password");
        return user;
    }

    @Test
    public void whenAddUserThenFindByIdAndGetThatUser() {
        User user = getUser("User 1");
        userRepository.add(user);
        var res = userRepository.findById(user.getId()).get();
        assertThat(res.getLogin()).isEqualTo(user.getLogin());
    }

    @Test
    public void whenAddUserWithSameLoginThenGetException() {
        User user1 = getUser("User 1");
        User user2 = getUser("User 1");
        userRepository.add(user1);
        assertThatThrownBy(() -> userRepository.add(user2))
                .isInstanceOf(javax.persistence.PersistenceException.class)
                .hasMessageContaining("org.hibernate.exception.ConstraintViolationException:"
                        + " could not execute statement");
    }

    @Test
    public void whenUpdateUserThenGetUpdated() {
        User user1 = getUser("User 1");
        User user2 = getUser("User 2");
        userRepository.add(user1);
        user2.setId(user1.getId());
        userRepository.update(user2);
        User res = userRepository.findById(user1.getId()).get();
        assertThat(res.getLogin()).isEqualTo(user2.getLogin());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        User user = getUser("User 1");
        userRepository.add(user);
        assertThat(userRepository.findById(user.getId()).get().getLogin()).isEqualTo(user.getLogin());
        userRepository.delete(user.getId());
        Optional<User> res = userRepository.findById(user.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByLoginThenGetUserWithThatLogin() {
        User user = getUser("User 1");
        userRepository.add(user);
        String res = userRepository.findByLogin(user.getLogin()).get().getLogin();
        assertThat(res).isEqualTo(user.getLogin());
    }

    @Test
    public void whenFindByFakeLoginThenGetEmpty() {
        User user = getUser("User 1");
        userRepository.add(user);
        String fakeLogin = user.getLogin() + "fake";
        Optional<User> res = userRepository.findByLogin(fakeLogin);
        assertThat(res).isEmpty();
    }

    @Test
    public void whenFindByLikeLoginThenGetUsers() {
        User user1 = getUser("User 1");
        User user2 = getUser("User 2");
        User user3 = getUser("BCA");
        userRepository.add(user1);
        userRepository.add(user2);
        userRepository.add(user3);
        String searchLogin = user1.getLogin().substring(0, 4);
        List<User> res = userRepository.findByLikeLogin(searchLogin);
        assertThat(res).containsExactly(user1, user2);
        assertThat(res.get(0).getLogin()).isEqualTo(user1.getLogin());
        assertThat(res.get(1).getLogin()).isEqualTo(user2.getLogin());
        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    public void whenAddTwoUsersFindAllThenGetTwoUsers() {
        User user1 = getUser("User 1");
        User user2 = getUser("User 2");
        userRepository.add(user1);
        userRepository.add(user2);
        var res = userRepository.findAllOrderById();
        assertThat(res.get(0).getLogin()).isEqualTo(user1.getLogin());
        assertThat(res.get(1).getLogin()).isEqualTo(user2.getLogin());
    }

}