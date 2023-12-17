package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateEngineRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddEngineThenFindByIdAndGetThatEngine() {
        Engine engine = util.getEngine("Engine 1");
        util.engineRepository.add(engine);
        var res = util.engineRepository.findById(engine.getId()).get();
        assertThat(res.getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenUpdateCarThenGetUpdated() {
        Engine engine1 = util.getEngine("Engine 1");
        Engine engine2 = util.getEngine("Engine 2");
        util.engineRepository.add(engine1);
        engine2.setId(engine1.getId());
        util.engineRepository.update(engine2);
        Engine res = util.engineRepository.findById(engine1.getId()).get();
        assertThat(res.getName()).isEqualTo(engine2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Engine engine = util.getEngine("Engine 1");
        util.engineRepository.add(engine);
        assertThat(util.engineRepository.findById(engine.getId()).get()).isEqualTo(engine);
        util.engineRepository.deleteById(engine.getId());
        Optional<Engine> res = util.engineRepository.findById(engine.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoCarsFindAllThenGetTwoCars() {
        Engine engine1 = util.getEngine("Engine 1");
        Engine engine2 = util.getEngine("Engine 2");
        util.engineRepository.add(engine1);
        util.engineRepository.add(engine2);
        var res = util.engineRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(engine1, engine2);
    }

}