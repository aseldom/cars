package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.HistoryOwner;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

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
        Car car1 = util.getCar("Car 1", "Vin1");
        User user1 = util.getUser("User1");
        Owner owner1 = util.getOwner("Owner 1", user1);
        util.carRepository.add(car1);
        util.ownerRepository.add(owner1);
        HistoryOwner historyOwner = util.getHistoryOwner(car1, owner1);
        util.historyOwnerRepository.add(historyOwner);
        HistoryOwner res = util.historyOwnerRepository.findById(historyOwner.getId()).get();
        assertThat(res).isEqualTo(historyOwner);
    }

    @Test
    public void whenUpdateThenGetUpdated() {
        Car car1 = util.getCar("Car 1", "Vin1");
        User user1 = util.getUser("User1");
        Owner owner1 = util.getOwner("Owner 1", user1);
        util.carRepository.add(car1);
        util.ownerRepository.add(owner1);
        HistoryOwner historyOwner1 = util.getHistoryOwner(car1, owner1);
        HistoryOwner historyOwner2 = util.getHistoryOwner(car1, owner1);
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
        Car car1 = util.getCar("Car 1", "Vin1");
        User user1 = util.getUser("User1");
        Owner owner1 = util.getOwner("Owner 1", user1);
        util.carRepository.add(car1);
        util.ownerRepository.add(owner1);
        HistoryOwner historyOwner = util.getHistoryOwner(car1, owner1);
        util.historyOwnerRepository.add(historyOwner);
        assertThat(util.historyOwnerRepository.findById(historyOwner.getId()).get()).isEqualTo(historyOwner);
        util.historyOwnerRepository.deleteById(historyOwner.getId());
        Optional<HistoryOwner> res = util.historyOwnerRepository.findById(historyOwner.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoHistoryOwnersFindAllThenGetTwoHistoryOwners() {
        Car car1 = util.getCar("Car 1", "Vin1");
        Car car2 = util.getCar("Car 2", "Vin2");
        User user1 = util.getUser("User1");
        User user2 = util.getUser("User2");
        Owner owner1 = util.getOwner("Owner 1", user1);
        Owner owner2 = util.getOwner("Owner 2", user2);
        util.carRepository.add(car1);
        util.carRepository.add(car2);
        util.ownerRepository.add(owner1);
        util.ownerRepository.add(owner2);
        HistoryOwner historyOwner1 = util.getHistoryOwner(car1, owner1);
        HistoryOwner historyOwner2 = util.getHistoryOwner(car2, owner2);
        util.historyOwnerRepository.add(historyOwner1);
        util.historyOwnerRepository.add(historyOwner2);
        var res = util.historyOwnerRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(historyOwner1, historyOwner2);
    }

}