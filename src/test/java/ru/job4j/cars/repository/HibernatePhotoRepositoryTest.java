package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Photo;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePhotoRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddPhotoThenFindByIdAndGetThatPhoto() {
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Photo photo2 = util.getPhoto("Photo 2", "Path 2");
        util.photoRepository.add(photo1);
        util.photoRepository.add(photo2);
        Photo res1 = util.photoRepository.findById(photo1.getId()).get();
        Photo res2 = util.photoRepository.findById(photo2.getId()).get();
        assertThat(res1).isEqualTo(photo1);
        assertThat(res2).isEqualTo(photo2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        util.photoRepository.add(photo1);
        assertThat(util.photoRepository.findById(photo1.getId()).get()).isEqualTo(photo1);
        util.photoRepository.deleteById(photo1.getId());
        Optional<Photo> res = util.photoRepository.findById(photo1.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPhotosFindAllThenGetTwoPhotos() {
        Photo photo1 = util.getPhoto("Photo 1", "Path 1");
        Photo photo2 = util.getPhoto("Photo 2", "Path 2");
        util.photoRepository.add(photo1);
        util.photoRepository.add(photo2);
        Collection<Photo> res = util.photoRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(photo1, photo2);
    }

    @Test
    public void empty() {

    }

}