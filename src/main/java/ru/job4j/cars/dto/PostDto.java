package ru.job4j.cars.dto;

import lombok.*;
import ru.job4j.cars.model.Photo;

import java.util.List;

@Builder(builderMethodName = "of")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

    private int id;
    private String carModel;
    private String description;
    private int year;
    private int mileage;
    private String engine;
    private List<Photo> photos;
    private Long price;
    private boolean status;


}
