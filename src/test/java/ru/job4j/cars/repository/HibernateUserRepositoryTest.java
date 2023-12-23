package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(res.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void whenAddUserWithSameLoginThenReturnEmpty() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 1");
        util.userRepository.add(user1);
        var res = util.userRepository.add(user2);
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenUpdateUserThenGetUpdated() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        user2.setId(user1.getId());
        util.userRepository.update(user2);
        User res = util.userRepository.findById(user1.getId()).get();
        assertThat(res.getEmail()).isEqualTo(user2.getEmail());
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
        assertThat(util.userRepository.findById(user.getId()).get().getEmail()).isEqualTo(user.getEmail());
        util.userRepository.delete(user.getId());
        Optional<User> res = util.userRepository.findById(user.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByLoginThenGetUserWithThatLogin() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        String res = util.userRepository.findByLogin(user.getEmail()).get().getEmail();
        assertThat(res).isEqualTo(user.getEmail());
    }

    @Test
    public void whenFindByLoginAndPasswordThenGetUserWithThatLoginAndPassword() {
        User user1 = util.getUser("User 1", "Password 1");
        User user2 = util.getUser("User 2", "Password 2");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        String res1 = util.userRepository.findByLoginAndPassword(user1.getEmail(), user1.getPassword()).get().getEmail();
        String res2 = util.userRepository.findByLoginAndPassword(user2.getEmail(), user2.getPassword()).get().getEmail();
        assertThat(res1).isEqualTo(user1.getEmail());
        assertThat(res2).isEqualTo(user2.getEmail());
    }

    @Test
    public void whenFindByFakeLoginThenGetEmpty() {
        User user = util.getUser("User 1");
        util.userRepository.add(user);
        String fakeLogin = user.getEmail() + "fake";
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
        String searchLogin = user1.getEmail().substring(0, 4);
        List<User> res = util.userRepository.findByLikeLogin(searchLogin);
        assertThat(res).containsExactly(user1, user2);
        assertThat(res.get(0).getEmail()).isEqualTo(user1.getEmail());
        assertThat(res.get(1).getEmail()).isEqualTo(user2.getEmail());
        assertThat(res.size()).isEqualTo(2);
    }

    @Test
    public void whenAddTwoUsersFindAllThenGetTwoUsers() {
        User user1 = util.getUser("User 1");
        User user2 = util.getUser("User 2");
        util.userRepository.add(user1);
        util.userRepository.add(user2);
        var res = util.userRepository.findAllOrderById();
        assertThat(res.get(0).getEmail()).isEqualTo(user1.getEmail());
        assertThat(res.get(1).getEmail()).isEqualTo(user2.getEmail());
    }

}