package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateOwnerRepositoryTest {

    private final Util util = new Util();

    @AfterEach
    public void clearRepository() {
        util.clearRepository();
    }

    @Test
    public void whenAddOwnerThenFindByIdAndGetThatOwner() {
        Owner owner1 = util.getOwner("Owner 1");
        util.ownerRepository.add(owner1);
        var res = util.ownerRepository.findById(owner1.getId()).get();
        assertThat(res.getName()).isEqualTo(owner1.getName());
    }

    @Test
    public void whenUpdateOwnerThenGetUpdated() {
        Owner owner1 = util.getOwner("Owner 1");
        Owner owner2 = util.getOwner("Owner 2");
        util.ownerRepository.add(owner1);
        owner2.setId(owner1.getId());
        util.ownerRepository.update(owner2);
        Owner res = util.ownerRepository.findById(owner1.getId()).get();
        assertThat(res.getName()).isEqualTo(owner2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Owner owner = util.getOwner("Owner 1");
        util.ownerRepository.add(owner);
        assertThat(util.ownerRepository.findById(owner.getId()).get()).isEqualTo(owner);
        util.ownerRepository.deleteById(owner.getId());
        Optional<Owner> res = util.ownerRepository.findById(owner.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoOwnersFindAllThenGetTwoOwners() {
        Owner owner1 = util.getOwner("Owner 1");
        Owner owner2 = util.getOwner("Owner 2");
        util.ownerRepository.add(owner1);
        util.ownerRepository.add(owner2);
        var res = util.ownerRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(owner1, owner2);
    }

}