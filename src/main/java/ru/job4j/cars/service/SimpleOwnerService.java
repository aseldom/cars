package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.HibernateOwnerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleOwnerService implements OwnerService {

    private final HibernateOwnerRepository hibernateOwnerRepository;

    @Override
    public Optional<Owner> add(Owner owner) {
        return hibernateOwnerRepository.add(owner);
    }

    @Override
    public Optional<Owner> update(Owner owner) {
        return hibernateOwnerRepository.update(owner);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernateOwnerRepository.deleteById(id);
    }

    @Override
    public Optional<Owner> findById(int id) {
        return hibernateOwnerRepository.findById(id);
    }

    @Override
    public Collection<Owner> findAll() {
        return hibernateOwnerRepository.findAll();
    }

}
