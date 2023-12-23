package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    private String number;

    @Column(name = "engine_value")
    private Double engineValue;

    private Double power;

    @ManyToOne
    @JoinColumn(name = "engine_type_id")
    private EngineType engineType;

    public Engine(String number, Double engineValue, Double power, EngineType engineType) {
        this.number = number;
        this.engineValue = engineValue;
        this.power = power;
        this.engineType = engineType;
    }
}