package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "transmissions")
public class Transmission {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

}
