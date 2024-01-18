package ru.job4j.cars.repository.pricehistory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePriceHistoryRepository implements PriceHistoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<PriceHistory> add(PriceHistory priceHistory) {
        crudRepository.run(session -> session.persist(priceHistory));
        return Optional.of(priceHistory);
    }

    @Override
    public Optional<PriceHistory> update(PriceHistory priceHistory) {
        crudRepository.run(session -> session.merge(priceHistory));
        return Optional.of(priceHistory);
    }

    @Override
    public boolean deleteById(int id) {
        int res = crudRepository.runWithConfirm(
                "DELETE FROM PriceHistory WHERE id = :fId",
                Map.of("fId", id)
        );
        return res != 0;
    }

    @Override
    public Optional<PriceHistory> findById(int id) {
        return crudRepository.optional("FROM PriceHistory p WHERE p.id = :fId",
                PriceHistory.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<PriceHistory> findAll() {
        return crudRepository.query("FROM PriceHistory", PriceHistory.class);
    }

}
