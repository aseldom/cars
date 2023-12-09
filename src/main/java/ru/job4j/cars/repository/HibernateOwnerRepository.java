package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HibernateOwnerRepository implements OwnerRepository {

    CrudRepository crudRepository;

    @Override
    public Optional<Owner> add(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return Optional.of(owner);
    }

    @Override
    public Optional<Owner> update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
        return Optional.of(owner);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE FROM Owner WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "FROM Owner WHERE id = :fId",
                Owner.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Owner> findAll() {
        return crudRepository.query("FROM Owner", Owner.class);
    }
}
