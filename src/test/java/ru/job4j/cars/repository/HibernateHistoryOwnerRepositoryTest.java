package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateHistoryOwnerRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HistoryOwnerRepository historyOwnerRepository = new HibernateHistoryOwnerRepository(new CrudRepository(sf));
    private final UserRepository userRepository = new HibernateUserRepository(new CrudRepository(sf));
    private final CarRepository carRepository = new HibernateCarRepository(new CrudRepository(sf));
    private final OwnerRepository ownerRepository = new HibernateOwnerRepository(new CrudRepository(sf));

    @AfterEach
    public void clearHistoryOwnerRepository() {
        for (HistoryOwner e : historyOwnerRepository.findAll()) {
            historyOwnerRepository.deleteById(e.getId());
        }
        for (Owner e : ownerRepository.findAll()) {
            ownerRepository.deleteById(e.getId());
        }
        for (var e : userRepository.findAllOrderById()) {
            userRepository.delete(e.getId());
        }
    }

    private HistoryOwner getHistoryOwner(String name) {
        HistoryOwner historyOwner = new HistoryOwner();
        historyOwner.setOwner(getOwner(name));
        historyOwner.setCar(getCar("Car 1 " + name));
        return historyOwner;
    }

    private Owner getOwner(String name) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setUser(getUser("login" + name));
        ownerRepository.add(owner);
        return owner;
    }

    private User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("password");
        return user;
    }

    private Car getCar(String name) {
        Car car = new Car();
        car.setName(name);
        car.setEngine(getEngine());
        carRepository.add(car);
        return car;
    }

    private Engine getEngine() {
        Engine engine = new Engine();
        engine.setName("Engine 1");
        return engine;
    }

    @Test
    public void whenAddThenFindByIdAndGetThatHistoryOwner() {
        HistoryOwner historyOwner = getHistoryOwner("HistoryOwner 1");
        historyOwnerRepository.add(historyOwner);
        HistoryOwner res = historyOwnerRepository.findById(historyOwner.getId()).get();
        assertThat(res).isEqualTo(historyOwner);
    }

    @Test
    public void whenUpdateThenGetUpdated() {
        HistoryOwner historyOwner1 = getHistoryOwner("HistoryOwner 1");
        HistoryOwner historyOwner2 = getHistoryOwner("HistoryOwner 2");
        historyOwnerRepository.add(historyOwner1);
        historyOwner2.setId(historyOwner1.getId());
        historyOwnerRepository.update(historyOwner2);
        HistoryOwner res = historyOwnerRepository.findById(historyOwner1.getId()).get();
        assertThat(res).isEqualTo(historyOwner2);
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        HistoryOwner historyOwner = getHistoryOwner("HistoryOwner 1");
        historyOwnerRepository.add(historyOwner);
        assertThat(historyOwnerRepository.findById(historyOwner.getId()).get()).isEqualTo(historyOwner);
        historyOwnerRepository.deleteById(historyOwner.getId());
        Optional<HistoryOwner> res = historyOwnerRepository.findById(historyOwner.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoHistoryOwnersFindAllThenGetTwoHistoryOwners() {
        HistoryOwner historyOwner1 = getHistoryOwner("HistoryOwner 1");
        HistoryOwner historyOwner2 = getHistoryOwner("HistoryOwner 2");
        historyOwnerRepository.add(historyOwner1);
        historyOwnerRepository.add(historyOwner2);
        var res = historyOwnerRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(historyOwner1, historyOwner2);
    }

}