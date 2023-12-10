package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateOwnerRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final HibernateOwnerRepository ownerRepository = new HibernateOwnerRepository(new CrudRepository(sf));
    private final HibernateUserRepository userRepository = new HibernateUserRepository(new CrudRepository(sf));

    @AfterEach
    public void clearRepository() {
        for (var e : ownerRepository.findAll()) {
            ownerRepository.deleteById(e.getId());
        }
        for (var e : userRepository.findAllOrderById()) {
            userRepository.delete(e.getId());
        }
    }

    private static Owner getOwner(String name) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setUser(getUser("login" + name));
        return owner;
    }

    private static User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("password");
        return user;
    }

    @Test
    public void whenAddOwnerThenFindByIdAndGetThatOwner() {
        Owner owner = getOwner("Owner 1");
        ownerRepository.add(owner);
        var res = ownerRepository.findById(owner.getId()).get();
        assertThat(res.getName()).isEqualTo(owner.getName());
    }

    @Test
    public void whenUpdateOwnerThenGetUpdated() {
        Owner owner1 = getOwner("Owner 1");
        Owner owner2 = getOwner("Owner 2");
        ownerRepository.add(owner1);
        owner2.setId(owner1.getId());
        ownerRepository.update(owner2);
        Owner res = ownerRepository.findById(owner1.getId()).get();
        assertThat(res.getName()).isEqualTo(owner2.getName());
    }

    @Test
    public void whenDeleteByIdThenGetEmpty() {
        Owner owner = getOwner("Owner 1");
        ownerRepository.add(owner);
        assertThat(ownerRepository.findById(owner.getId()).get()).isEqualTo(owner);
        ownerRepository.deleteById(owner.getId());
        Optional<Owner> res = ownerRepository.findById(owner.getId());
        assertThat(res).isEqualTo(Optional.empty());
    }

    @Test
    public void whenAddTwoOwnersFindAllThenGetTwoOwners() {
        Owner owner1 = getOwner("Owner 1");
        Owner owner2 = getOwner("Owner 2");
        ownerRepository.add(owner1);
        ownerRepository.add(owner2);
        var res = ownerRepository.findAll();
        assertThat(res).containsExactlyInAnyOrder(owner1, owner2);
    }

}