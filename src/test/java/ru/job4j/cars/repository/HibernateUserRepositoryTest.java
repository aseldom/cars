package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HibernateUserRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddUserThenFindByIdAndGetThatUser() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        var res = util.userRepository.findById(user.getId()).get();
        assertThat(res.getLogin()).isEqualTo(user.getLogin());
    }

    @Test
    public void whenAddUserWithSameLoginThenGetException() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 1");
        util.userRepository.add(user1);
        assertThatThrownBy(() -> util.userRepository.add(user2))
                .isInstanceOf(javax.persistence.PersistenceException.class)
                .hasMessageContaining("org.hibernate.exception.ConstraintViolationException:"
                        + " could not execute statement");
    }

    @Test
    public void whenUpdateUserThenGetUpdated() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        user2.setId(user1.getId());
        util.userRepository.update(user2);
        User res = util.userRepository.findById(user1.getId()).get();
        assertThat(res.getLogin()).isEqualTo(user2.getLogin());
    }

    @Test
    public void whenFindByLikeLoginThenGetTwoUsers() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        var res = util.userRepository.findByLikeLogin("User");
        assertThat(res).containsExactlyInAnyOrder(user1, user2);
    }

    @Test
    public void whenFindByLikeLoginThenGetZeroUsers() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        var res = util.userRepository.findByLikeLogin("Uses");
        assertThat(res.size()).isZero();
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        assertThat(util.userRepository.findById(user.getId()).get().getLogin()).isEqualTo(user.getLogin());
        util.userRepository.delete(user.getId());
        Optional<User> res = util.userRepository.findById(user.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByLoginThenGetUserWithThatLogin() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        String res = util.userRepository.findByLogin(user.getLogin()).get().getLogin();
        assertThat(res).isEqualTo(user.getLogin());
    }

    @Test
    public void whenFindByFakeLoginThenGetEmpty() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        String fakeLogin = user.getLogin() + "fake";
        Optional<User> res = util.userRepository.findByLogin(fakeLogin);
        assertThat(res).isEmpty();
    }

    @Test
    public void whenFindByLikeLoginThenGetUsers() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        User user3 = util.getUser("BCA");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        util.userRepository.add(user3);
        String searchLogin = user1.getLogin().substring(0, 4);
        List<User> res = util.userRepository.findByLikeLogin(searchLogin);
        assertThat(res).containsExactly(user1, user2);
        assertThat(res.get(0).getLogin()).isEqualTo(user1.getLogin());
        assertThat(res.get(1).getLogin()).isEqualTo(user2.getLogin());
        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    public void whenAddTwoUsersFindAllThenGetTwoUsers() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        var res = util.userRepository.findAllOrderById();
        assertThat(res.get(0).getLogin()).isEqualTo(user1.getLogin());
        assertThat(res.get(1).getLogin()).isEqualTo(user2.getLogin());
    }

}