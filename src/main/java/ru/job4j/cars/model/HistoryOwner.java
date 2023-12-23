package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "history_owner")
public class HistoryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne()
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private Timestamp startAt;

    private Timestamp endAt = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));

    @Override
    public String toString() {
        return "HistoryOwner{"
                + "id=" + id
                + ", startAt=" + startAt
                + ", endAt=" + endAt
                + '}';
    }
}
