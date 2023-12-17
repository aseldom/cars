package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePostRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddPostThenFindByIdAndGetThatPost() {
        Car car = util.getCar("Car1", "vin1");
        User user = util.getUser("user1");
        Post post = util.getPost(car, user, "Post 1");
        util.postRepository.add(post);
        var res = util.postRepository.findById(post.getId()).get();
        assertThat(res).isEqualTo(post);
    }

    @Test
    public void whenUpdatePostThenGetUpdated() {
        Car car1 = util.getCar("Car1", "vin1");
        Car car2 = util.getCar("Car2", "vin2");
        User user1 = util.getUser("user1");
        User user2 = util.getUser("user2");
        Post post1 = util.getPost(car1, user1, "Post 1");
        Post post2 = util.getPost(car2, user2, "Post 2");
        util.postRepository.add(post1);
        post2.setId(post1.getId());
        util.postRepository.update(post2);
        Post res = util.postRepository.findById(post1.getId()).get();
        assertThat(res).isEqualTo(post2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Car car = util.getCar("Car1", "vin1");
        User user = util.getUser("user1");
        Post post = util.getPost(car, user, "Post 1");
        util.postRepository.add(post);
        assertThat(util.postRepository.findById(post.getId()).get()).isEqualTo(post);
        util.postRepository.deleteById(post.getId());
        Optional<Post> res = util.postRepository.findById(post.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPostsFindAllThenGetTwoPosts() {
        Car car1 = util.getCar("Car1", "vin1");
        Car car2 = util.getCar("Car2", "vin2");
        User user1 = util.getUser("user1");
        User user2 = util.getUser("user2");
        Post post1 = util.getPost(car1, user1, "Post 1");
        Post post2 = util.getPost(car2, user2, "Post 2");
        util.postRepository.add(post1);
        util.postRepository.add(post2);
        var res = util.postRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(post1, post2);
    }

    @Test
    public void whenAddPostWithPhotoThenFindPostWithPhotoThenGetIt() {
        Car car1 = util.getCar("Car1", "vin1");
        Car car2 = util.getCar("Car2", "vin2");
        User user1 = util.getUser("user1");
        User user2 = util.getUser("user2");
        Photo photo1 = util.getPhoto("photo1", "path1");
        Post post1 = util.getPost(car1, user1, "Post 1", photo1);
        Post post2 = util.getPost(car2, user2, "Post 2");
        util.postRepository.add(post1);
        util.postRepository.add(post2);
        var res = util.postRepository.findWithPhoto();
        assertThat(res).containsExactlyInAnyOrder(post1);
    }

    @Test
    public void whenAddThreePostsThenGetPostForLastDay() {
        Car car1 = util.getCar("Car1", "vin1");
        Car car2 = util.getCar("Car2", "vin2");
        Car car3 = util.getCar("Car3", "vin3");
        User user1 = util.getUser("user1");
        User user2 = util.getUser("user2");
        User user3 = util.getUser("user3");
        Post post1 = util.getPost(car1, user1, "Post 1");
        Post post2 = util.getPost(car2, user2, "Post 2");
        Post post3 = util.getPost(car3, user3, "Post 3");
        post2.setCreated(Timestamp.valueOf(LocalDateTime.now().minusHours(23).minusMinutes(58)));
        post3.setCreated(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
        util.postRepository.add(post1);
        util.postRepository.add(post2);
        util.postRepository.add(post3);
        var res = util.postRepository.findLastDay();
        assertThat(res).containsExactlyInAnyOrder(post1, post2);
    }

    @Test
    public void empty() {

    }

}