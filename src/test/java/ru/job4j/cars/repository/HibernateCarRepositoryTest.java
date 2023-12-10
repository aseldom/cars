package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateCarRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HibernateCarRepository repository = new HibernateCarRepository(new CrudRepository(sf));

    @AfterEach
    public void clearCarRepository() {
        for (Car car : repository.findAll()) {
            repository.deleteById(car.getId());
        }
    }

    private Car getCar(String name) {
        Car car = new Car();
        car.setName(name);
        car.setEngine(getEngine());
        return car;
    }

    private Engine getEngine() {
        Engine engine = new Engine();
        engine.setName("Engine 1");
        return engine;
    }

    @Test
    public void whenAddCarThenFindByIdAndGetThatCar() {
        Car car = getCar("Car 1");
        repository.add(car);
        Car res = repository.findById(car.getId()).get();
        assertThat(res).isEqualTo(car);
    }

    @Test
    public void whenUpdateCarThenGetUpdated() {
        Car car1 = getCar("Car 1");
        Car car2 = getCar("Car 2");
        repository.add(car1);
        car2.setId(car1.getId());
        repository.update(car2);
        Car res = repository.findById(car1.getId()).get();
        assertThat(res.getName()).isEqualTo(car2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Car car = getCar("Car 1");
        repository.add(car);
        assertThat(repository.findById(car.getId()).get()).isEqualTo(car);
        repository.deleteById(car.getId());
        Optional<Car> res = repository.findById(car.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoCarsFindAllThenGetTwoCars() {
        Car car1 = getCar("Car 1");
        Car car2 = getCar("Car 2");
        repository.add(car1);
        repository.add(car2);
        var res = repository.findAll();
        assertThat(res).containsExactlyInAnyOrder(car1, car2);
    }

}