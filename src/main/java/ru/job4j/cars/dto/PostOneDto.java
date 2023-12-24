package ru.job4j.cars.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.job4j.cars.model.HistoryOwner;
import ru.job4j.cars.model.PriceHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostOneDto {

    private String carName;
    private String carVin;
    private int carProductionYear;
    private int carMileage;
    private String carColor;
    private String carTransmission;
    private String carWheelDrive;
    private String carBody;
    private String carEngine;
    private Long carPrice;
    private String engineNumber;
    private Double engineValue;
    private Double enginePower;
    private String postDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate carOwnerStart;
    private boolean postSale;
    private Set<HistoryOwner> historyOwners = new HashSet<>();
    private List<PriceHistory> priceHistories = new ArrayList<>();

}
