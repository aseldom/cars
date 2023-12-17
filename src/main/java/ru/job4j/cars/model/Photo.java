package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "auto_post_id")
    private Post post;

}
