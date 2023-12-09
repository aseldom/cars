package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history_owner")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class HistoryOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    Car car;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Owner owner;

    Timestamp startAt;

    Timestamp endAt;

}
