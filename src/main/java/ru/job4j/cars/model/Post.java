package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String description;

    private Timestamp created = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> histories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<Photo> photos = new ArrayList<>();

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", user=" + user
                + ", car=" + car
                + '}';
    }
}
