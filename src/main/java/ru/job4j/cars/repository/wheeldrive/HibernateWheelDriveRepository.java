package ru.job4j.cars.repository.wheeldrive;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.WheelDrive;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateWheelDriveRepository implements WheelDriveRepository {

    private final CrudRepository crudRepository;

    @Override
    public Optional<WheelDrive> findById(int id) {
        return crudRepository.optional("FROM WheelDrive c WHERE c.id = :fId",
                WheelDrive.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<WheelDrive> findAll() {
        return crudRepository.query("FROM WheelDrive", WheelDrive.class);
    }

}
