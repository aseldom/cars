package ru.job4j.cars.repository.transmission;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Transmission;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateTransmissionRepository implements TransmissionRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Transmission> findById(int id) {
        return crudRepository.optional("FROM Transmission c WHERE c.id = :fId",
                Transmission.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Transmission> findAll() {
        return crudRepository.query("FROM Transmission", Transmission.class);
    }

}
