package ru.job4j.cars.service;

import ru.job4j.cars.model.HistoryOwner;

import java.util.Collection;
import java.util.Optional;

public interface HistoryOwnerService {

    Optional<HistoryOwner> add(HistoryOwner historyOwner);

    Optional<HistoryOwner> update(HistoryOwner historyOwner);

    boolean deleteById(int id);

    Optional<HistoryOwner> findById(int id);

    Collection<HistoryOwner> findAll();

}
