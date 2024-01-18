package ru.job4j.cars.repository.historyowner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.HistoryOwner;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateHistoryOwnerRepository implements HistoryOwnerRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<HistoryOwner> add(HistoryOwner historyOwner) {
        crudRepository.run(session -> session.persist(historyOwner));
        return Optional.of(historyOwner);
    }

    @Override
    public Optional<HistoryOwner> update(HistoryOwner historyOwner) {
        crudRepository.run(session -> session.merge(historyOwner));
        return Optional.of(historyOwner);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE FROM HistoryOwner WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<HistoryOwner> findById(int id) {
        return crudRepository.optional("FROM HistoryOwner h WHERE h.id = :fId",
                HistoryOwner.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<HistoryOwner> findAll() {
        return crudRepository.query("FROM HistoryOwner", HistoryOwner.class);
    }

}
