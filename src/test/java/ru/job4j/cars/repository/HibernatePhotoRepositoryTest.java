package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePhotoRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearPhotoRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddPhotoThenFindByIdAndGetThatPhoto() {
        Car car = util.getCar("Car1", "vin1");
        User user = util.getUser("user1");
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Photo photo2 = util.getPhoto("Photo 2", "Path 2");
        Post post = util.getPost(car, user, "Post 1", photo1);
        util.postRepository.add(post);
        photo2.setPost(post);
        post.getPhotos().add(photo2);
        util.photoRepository.add(photo2);
        Photo res1 = util.photoRepository.findById(photo1.getId()).get();
        Photo res2 = util.photoRepository.findById(photo2.getId()).get();
        assertThat(res1).isEqualTo(photo1);
        assertThat(res2).isEqualTo(photo2);
    }

    @Test
    public void whenUpdatePhotoThenGetUpdated() {
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Photo photo2 = util.getPhoto("Photo 2", "Path 2");
        Car car1 = util.getCar("Car1", "vin1");
        User user1 = util.getUser("user1");
        Post post1 = util.getPost(car1, user1, "Post 1", photo1);
        util.postRepository.add(post1);
        photo2.setId(photo1.getId());
        photo2.setPost(photo1.getPost());
        util.photoRepository.update(photo2);
        Photo res = util.photoRepository.findById(photo1.getId()).get();
        assertThat(res.getName()).isEqualTo(photo2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Car car1 = util.getCar("Car1", "vin1");
        User user1 = util.getUser("user1");
        Post post1 = util.getPost(car1, user1, "Post 1", photo1);
        util.postRepository.add(post1);
        assertThat(util.photoRepository.findById(photo1.getId()).get()).isEqualTo(photo1);
        util.photoRepository.deleteById(photo1.getId());
        Optional<Photo> res = util.photoRepository.findById(photo1.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPhotosFindAllThenGetTwoPhotos() {
        Car car = util.getCar("Car1", "vin1");
        User user = util.getUser("user1");
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Photo photo2 = util.getPhoto("Photo 2", "Path 2");
        Post post = util.getPost(car, user, "Post 1", photo1);
        util.postRepository.add(post);
        photo2.setPost(post);
        post.getPhotos().add(photo2);
        util.photoRepository.add(photo2);
        Collection<Photo> res = util.photoRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(photo1, photo2);
    }

    @Test
    public void empty() {

    }

}