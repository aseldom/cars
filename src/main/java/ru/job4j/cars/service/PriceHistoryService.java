package ru.job4j.cars.service;

import ru.job4j.cars.model.PriceHistory;

import java.util.Collection;
import java.util.Optional;

public interface PriceHistoryService {

    Optional<PriceHistory> add(PriceHistory priceHistory);

    Optional<PriceHistory> update(PriceHistory priceHistory);

    boolean deleteById(int id);

    Optional<PriceHistory> findById(int id);

    Collection<PriceHistory> findAll();

}
