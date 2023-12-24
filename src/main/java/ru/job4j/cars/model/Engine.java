package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
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