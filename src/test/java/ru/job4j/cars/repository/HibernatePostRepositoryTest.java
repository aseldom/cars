package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;

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
        Post post = util.getPost("Post 1");
        util.postRepository.add(post);
        var res = util.postRepository.findById(post.getId()).get();
        assertThat(res).isEqualTo(post);
    }

    @Test
    public void whenUpdatePostThenGetUpdated() {
        Post post1 = util.getPost("Post 1");
        Post post2 = util.getPost("Post 2");
        util.postRepository.add(post1);
        post2.setId(post1.getId());
        util.postRepository.update(post2);
        Post res = util.postRepository.findById(post1.getId()).get();
        assertThat(res).isEqualTo(post2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Post post = util.getPost("Post 1");
        util.postRepository.add(post);
        assertThat(util.postRepository.findById(post.getId()).get()).isEqualTo(post);
        util.postRepository.deleteById(post.getId());
        Optional<Post> res = util.postRepository.findById(post.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPostsFindAllThenGetTwoPosts() {
        Post post1 = util.getPost("Post 1");
        Post post2 = util.getPost("Post 2");
        util.postRepository.add(post1);
        util.postRepository.add(post2);
        var res = util.postRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(post1, post2);
    }

    @Test
    public void whenAddPostWithPhotoThenFindPostWithPhotoThenGetIt() {
        Photo photo1 = util.getPhoto("photo1", "path1");
        util.photoRepository.add(photo1);
        Post post1 = util.getPost("Post 1");
        Post post2 = util.getPost("Post 2");
        post1.getPhotos().add(photo1);
        util.postRepository.add(post1);
        util.postRepository.add(post2);
        var res = util.postRepository.findWithPhoto();
        assertThat(res).containsExactlyInAnyOrder(post1);
    }

    @Test
    public void whenAddThreePostsThenGetPostForLastDay() {
        Post post1 = util.getPost("Post 1");
        Post post2 = util.getPost("Post 2");
        Post post3 = util.getPost("Post 3");
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