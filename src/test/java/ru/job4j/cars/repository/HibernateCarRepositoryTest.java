package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateCarRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddCarThenFindByIdAndGetThatCar() {
        Car car1 = util.getCar("Car 1", "Vin1");
        util.carRepository.add(car1);
        Car res = util.carRepository.findById(car1.getId()).get();
        assertThat(res).isEqualTo(car1);
    }

    @Test
    public void whenUpdateCarThenGetUpdated() {
        Car car1 = util.getCar("Car 1", "Vin1");
        Car car2 = util.getCar("Car 2", "Vin2");
        util.carRepository.add(car1);
        car2.setId(car1.getId());
        util.carRepository.update(car2);
        Car res = util.carRepository.findById(car1.getId()).get();
        assertThat(res.getName()).isEqualTo(car2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Car car1 = util.getCar("Car 1", "Vin1");
        util.carRepository.add(car1);
        assertThat(util.carRepository.findById(car1.getId()).get()).isEqualTo(car1);
        util.carRepository.deleteById(car1.getId());
        Optional<Car> res = util.carRepository.findById(car1.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoCarsFindAllThenGetTwoCars() {
        Car car1 = util.getCar("Car 1", "Vin1");
        Car car2 = util.getCar("Car 2", "Vin2");
        util.carRepository.add(car1);
        util.carRepository.add(car2);
        var res = util.carRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(car1, car2);
    }

}