package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(builderMethodName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String vin;

    @Column(name = "production_year")
    private int productionYear;

    private int mileage;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "wheel_drive_id")
    private WheelDrive wheelDrive;

    @ManyToOne
    @JoinColumn(name = "car_body_id")
    private CarBody carBody;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<HistoryOwner> historyOwners = new HashSet<>();

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vin='" + vin + '\''
                + ", productionYear=" + productionYear
                + ", mileage=" + mileage
                + ", color=" + color
                + ", transmission=" + transmission
                + ", wheelDrive=" + wheelDrive
                + ", carBody=" + carBody
                + ", engine=" + engine
                + '}';
    }
}