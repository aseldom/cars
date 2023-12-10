package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.PriceHistory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePriceHistoryRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final PriceHistoryRepository repository = new HibernatePriceHistoryRepository(new CrudRepository(sf));

    @AfterEach
    public void clearPriceHistoryRepository() {
        for (PriceHistory priceHistory : repository.findAll()) {
            repository.deleteById(priceHistory.getId());
        }
    }

    private PriceHistory getPriceHistory(Long before, Long after) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(before);
        priceHistory.setAfter(after);
        return priceHistory;
    }

    @Test
    public void whenAddPriceHistoryThenFindByIdAndGetThatPriceHistory() {
        PriceHistory priceHistory = getPriceHistory(100L, 200L);
        repository.add(priceHistory);
        PriceHistory res = repository.findById(priceHistory.getId()).get();
        assertThat(res).isEqualTo(priceHistory);
    }

    @Test
    public void whenUpdatePriceHistoryThenGetUpdated() {
        PriceHistory priceHistory1 = getPriceHistory(100L, 200L);
        PriceHistory priceHistory2 = getPriceHistory(300L, 400L);
        repository.add(priceHistory1);
        priceHistory2.setId(priceHistory1.getId());
        repository.update(priceHistory2);
        PriceHistory res = repository.findById(priceHistory1.getId()).get();
        assertThat(res).isEqualTo(priceHistory2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        PriceHistory priceHistory = getPriceHistory(100L, 200L);
        repository.add(priceHistory);
        assertThat(repository.findById(priceHistory.getId()).get()).isEqualTo(priceHistory);
        repository.deleteById(priceHistory.getId());
        Optional<PriceHistory> res = repository.findById(priceHistory.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPriceHistoryFindAllThenGetTwoPriceHistory() {
        PriceHistory priceHistory1 = getPriceHistory(100L, 200L);
        PriceHistory priceHistory2 = getPriceHistory(300L, 400L);
        repository.add(priceHistory1);
        repository.add(priceHistory2);
        var res = repository.findAll();
        assertThat(res).containsExactlyInAnyOrder(priceHistory1, priceHistory2);
    }

}