package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Photo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePhotoRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final PhotoRepository repository = new HibernatePhotoRepository(new CrudRepository(sf));

    @AfterEach
    public void clearPhotoRepository() {
        for (Photo photo : repository.findAll()) {
            repository.deleteById(photo.getId());
        }
    }

    private Photo getPhoto(String name, String path) {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setPath(path);
        return photo;
    }

    @Test
    public void whenAddPhotoThenFindByIdAndGetThatPhoto() {
        Photo photo = getPhoto("Photo 1", "Path 1");
        repository.add(photo);
        Photo res = repository.findById(photo.getId()).get();
        assertThat(res).isEqualTo(photo);
    }

    @Test
    public void whenUpdatePhotoThenGetUpdated() {
        Photo photo1 = getPhoto("Photo 1", "Path 1");
        Photo photo2 = getPhoto("Photo 2", "Path 2");
        repository.add(photo1);
        photo2.setId(photo1.getId());
        repository.update(photo2);
        Photo res = repository.findById(photo1.getId()).get();
        assertThat(res.getName()).isEqualTo(photo2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Photo photo = getPhoto("Photo 1", "Path 1");
        repository.add(photo);
        assertThat(repository.findById(photo.getId()).get()).isEqualTo(photo);
        repository.deleteById(photo.getId());
        Optional<Photo> res = repository.findById(photo.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPhotosFindAllThenGetTwoPhotos() {
        Photo photo1 = getPhoto("Photo 1", "Path 1");
        Photo photo2 = getPhoto("Photo 2", "Path 2");
        repository.add(photo1);
        repository.add(photo2);
        var res = repository.findAll();
        assertThat(res).containsExactlyInAnyOrder(photo1, photo2);
    }

}