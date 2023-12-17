package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.HibernatePriceHistoryRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePriceHistoryService implements PriceHistoryService {

    private final HibernatePriceHistoryRepository hibernatePriceHistoryRepository;

    @Override
    public Optional<PriceHistory> add(PriceHistory priceHistory) {
        return hibernatePriceHistoryRepository.add(priceHistory);
    }

    @Override
    public Optional<PriceHistory> update(PriceHistory priceHistory) {
        return hibernatePriceHistoryRepository.update(priceHistory);
    }

    @Override
    public boolean deleteById(int id) {
        return hibernatePriceHistoryRepository.deleteById(id);
    }

    @Override
    public Optional<PriceHistory> findById(int id) {
        return hibernatePriceHistoryRepository.findById(id);
    }

    @Override
    public Collection<PriceHistory> findAll() {
        return hibernatePriceHistoryRepository.findAll();
    }

}
