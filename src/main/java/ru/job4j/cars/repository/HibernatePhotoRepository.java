package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePhotoRepository implements PhotoRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Photo> add(Photo photo) {
        crudRepository.run(session -> session.persist(photo));
        return Optional.of(photo);
    }

    @Override
    public Optional<Photo> update(Photo photo) {
        crudRepository.run(session -> session.merge(photo));
        return Optional.of(photo);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE FROM Photo WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<Photo> findById(int id) {
        return crudRepository.optional("FROM Photo p WHERE p.id = :fId",
                Photo.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Photo> findAll() {
        return crudRepository.query("FROM Photo", Photo.class);
    }

}
