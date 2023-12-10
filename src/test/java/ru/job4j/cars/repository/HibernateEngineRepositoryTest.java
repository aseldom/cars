package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateEngineRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HibernateEngineRepository repository = new HibernateEngineRepository(new CrudRepository(sf));

    @AfterEach
    public void clearRepository() {
        for (var e : repository.findAll()) {
            repository.deleteById(e.getId());
        }
    }

    private static Engine getEngine(String name) {
        Engine engine = new Engine();
        engine.setName(name);
        return engine;
    }

    @Test
    public void whenAddEngineThenFindByIdAndGetThatEngine() {
        Engine engine = getEngine("Engine 1");
        repository.add(engine);
        var res = repository.findById(engine.getId()).get();
        assertThat(res.getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenUpdateCarThenGetUpdated() {
        Engine engine1 = getEngine("Engine 1");
        Engine engine2 = getEngine("Engine 2");
        repository.add(engine1);
        engine2.setId(engine1.getId());
        repository.update(engine2);
        Engine res = repository.findById(engine1.getId()).get();
        assertThat(res.getName()).isEqualTo(engine2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Engine engine = getEngine("Engine 1");
        repository.add(engine);
        assertThat(repository.findById(engine.getId()).get()).isEqualTo(engine);
        repository.deleteById(engine.getId());
        Optional<Engine> res = repository.findById(engine.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoCarsFindAllThenGetTwoCars() {
        Engine engine1 = getEngine("Engine 1");
        Engine engine2 = getEngine("Engine 2");
        repository.add(engine1);
        repository.add(engine2);
        var res = repository.findAll();
        assertThat(res).containsExactlyInAnyOrder(engine1, engine2);
    }

}