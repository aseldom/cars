package ru.job4j.cars.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostCreateDto {

    private String carName;
    private String carVin;
    private int carProductionYear;
    private int carMileage;
    private int carColorId;
    private int carTransmissionId;
    private int carWheelDriveId;
    private int carBodyId;
    private int carEngineId;
    private Long carPrice;
    private String engineNumber;
    private Double engineValue;
    private Double enginePower;
    private String postDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate carOwnerStart;
    private boolean postSale;

}
