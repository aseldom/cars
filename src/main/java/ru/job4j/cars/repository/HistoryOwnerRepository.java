package ru.job4j.cars.repository;

import ru.job4j.cars.model.HistoryOwner;

import java.util.Collection;
import java.util.Optional;

public interface HistoryOwnerRepository {

    Optional<HistoryOwner> add(HistoryOwner historyOwner);

    Optional<HistoryOwner> update(HistoryOwner historyOwner);

    boolean deleteById(int id);

    Optional<HistoryOwner> findById(int id);

    Collection<HistoryOwner> findAll();

}
