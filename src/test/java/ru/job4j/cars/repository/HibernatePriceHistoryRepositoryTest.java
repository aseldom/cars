package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.PriceHistory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernatePriceHistoryRepositoryTest {
    
    private final Util util = new Util();

    @AfterEach
    public void clearPriceHistoryRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddPriceHistoryThenFindByIdAndGetThatPriceHistory() {
        PriceHistory priceHistory = util.getPriceHistory(100L, 200L);
        util.priceHistoryRepository.add(priceHistory);
        PriceHistory res = util.priceHistoryRepository.findById(priceHistory.getId()).get();
        assertThat(res).isEqualTo(priceHistory);
    }

    @Test
    public void whenUpdatePriceHistoryThenGetUpdated() {
        PriceHistory priceHistory1 = util.getPriceHistory(100L, 200L);
        PriceHistory priceHistory2 = util.getPriceHistory(300L, 400L);
        util.priceHistoryRepository.add(priceHistory1);
        priceHistory2.setId(priceHistory1.getId());
        util.priceHistoryRepository.update(priceHistory2);
        PriceHistory res = util.priceHistoryRepository.findById(priceHistory1.getId()).get();
        assertThat(res).isEqualTo(priceHistory2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        PriceHistory priceHistory = util.getPriceHistory(100L, 200L);
        util.priceHistoryRepository.add(priceHistory);
        assertThat(util.priceHistoryRepository.findById(priceHistory.getId()).get()).isEqualTo(priceHistory);
        util.priceHistoryRepository.deleteById(priceHistory.getId());
        Optional<PriceHistory> res = util.priceHistoryRepository.findById(priceHistory.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoPriceHistoryFindAllThenGetTwoPriceHistory() {
        PriceHistory priceHistory1 = util.getPriceHistory(100L, 200L);
        PriceHistory priceHistory2 = util.getPriceHistory(300L, 400L);
        util.priceHistoryRepository.add(priceHistory1);
        util.priceHistoryRepository.add(priceHistory2);
        var res = util.priceHistoryRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(priceHistory1, priceHistory2);
    }

    @Test
    public void empty() {

    }

}