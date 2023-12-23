package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HibernatePostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Post> add(Post post) {
        crudRepository.run(session -> session.persist(post));
        return Optional.of(post);
    }

    @Override
    public Optional<Post> update(Post post) {
        crudRepository.run(session -> session.merge(post));
        return Optional.of(post);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE from Post p  WHERE p.id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("FROM Post p LEFT JOIN FETCH p.priceHistories WHERE p.id = :fId",
                Post.class,
                Map.of("fId", id));
    }

    @Override
    public Collection<Post> findAll() {
        return crudRepository.query("FROM Post p LEFT JOIN FETCH p.priceHistories", Post.class);
    }

    @Override
    public Collection<Post> findLastDay() {
        Timestamp nowMinusDay = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        return crudRepository.query("FROM Post p LEFT JOIN FETCH p.priceHistories WHERE p.created >= :fNowMinusDay",
                Post.class,
                Map.of("fNowMinusDay", nowMinusDay));
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return crudRepository.query(
                "FROM Post p WHERE exists elements(p.photos)",
                Post.class
        );
    }

}
