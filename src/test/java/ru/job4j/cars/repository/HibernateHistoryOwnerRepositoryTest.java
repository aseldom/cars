package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.HistoryOwner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateHistoryOwnerRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddThenFindByIdAndGetThatHistoryOwner() {
        HistoryOwner historyOwner = util.getHistoryOwner();
        util.historyOwnerRepository.add(historyOwner);
        HistoryOwner res = util.historyOwnerRepository.findById(historyOwner.getId()).get();
        assertThat(res).isEqualTo(historyOwner);
    }

    @Test
    public void whenUpdateThenGetUpdated() {
        HistoryOwner historyOwner1 = util.getHistoryOwner();
        HistoryOwner historyOwner2 = util.getHistoryOwner();
        historyOwner2.setStartAt(Timestamp.valueOf(LocalDateTime.now().minusDays(3)));
        historyOwner2.setEndAt(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
        util.historyOwnerRepository.add(historyOwner1);
        historyOwner2.setId(historyOwner1.getId());
        util.historyOwnerRepository.update(historyOwner2);
        HistoryOwner res = util.historyOwnerRepository.findById(historyOwner1.getId()).get();
        assertThat(res).isEqualTo(historyOwner2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        HistoryOwner historyOwner = util.getHistoryOwner();
        util.historyOwnerRepository.add(historyOwner);
        assertThat(util.historyOwnerRepository.findById(historyOwner.getId()).get()).isEqualTo(historyOwner);
        util.historyOwnerRepository.deleteById(historyOwner.getId());
        Optional<HistoryOwner> res = util.historyOwnerRepository.findById(historyOwner.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoHistoryOwnersFindAllThenGetTwoHistoryOwners() {
        HistoryOwner historyOwner1 = util.getHistoryOwner();
        HistoryOwner historyOwner2 = util.getHistoryOwner();
        util.historyOwnerRepository.add(historyOwner1);
        util.historyOwnerRepository.add(historyOwner2);
        var res = util.historyOwnerRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(historyOwner1, historyOwner2);
    }

}