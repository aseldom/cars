package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.HistoryOwner;
import ru.job4j.cars.repository.historyowner.HibernateHistoryOwnerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleHistoryOwnerService implements HistoryOwnerService {

    private final HibernateHistoryOwnerRepository hibernateHistoryOwnerRepository;

    @Override
    public Optional<HistoryOwner> add(HistoryOwner historyOwner) {
        return hibernateHistoryOwnerRepository.add(historyOwner);
    }

    @Override
    public Optional<HistoryOwner> update(HistoryOwner historyOwner) {
        return hibernateHistoryOwnerRepository.update(historyOwner);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernateHistoryOwnerRepository.deleteById(id);
    }

    @Override
    public Optional<HistoryOwner> findById(int id) {
        return hibernateHistoryOwnerRepository.findById(id);
    }

    @Override
    public Collection<HistoryOwner> findAll() {
        return hibernateHistoryOwnerRepository.findAll();
    }
}
