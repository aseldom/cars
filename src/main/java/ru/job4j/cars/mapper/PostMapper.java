package ru.job4j.cars.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.cars.dto.PostCreateDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "car.name", target = "carModel")
    @Mapping(source = "car.productionYear", target = "year")
    @Mapping(source = "car.mileage", target = "mileage")
    @Mapping(source = "car.engine.number", target = "engine")
    @Mapping(source = "priceHistories", target = "price")
    @Mapping(source = "sales", target = "status")
    PostDto getPostDto(Post post);

    default Long priceHistoryToLong(List<PriceHistory> priceHistories) {
        return priceHistories.get(priceHistories.size() - 1).getAfter();
    }

    @Mapping(source = "postDescription", target = "description")
    Post getPostFromPostCreateDto(PostCreateDto postCreateDto);

    @Mapping(source = "source.carProductionYear", target = "productionYear")
    @Mapping(source = "source.carName", target = "name")
    @Mapping(source = "source.carMileage", target = "mileage")
    @Mapping(source = "source.carVin", target = "vin")
    @Mapping(source = "source", target = "engine")
    Car getCarFromPostCreateDto(PostCreateDto source);

    @Mapping(source = "engineNumber", target = "number")
    @Mapping(source = "enginePower", target = "power")
    Engine getEngineFromPostCreateDto(PostCreateDto source);

    @Mapping(source = "postCreateDto", target = "startAt")
    HistoryOwner getHistoryOwnerFromPostCreateDto(PostCreateDto postCreateDto, Owner owner);

    default Timestamp getTimeStamp(PostCreateDto postCreateDto) {
        return Timestamp.valueOf(postCreateDto.getCarOwnerStart().atStartOfDay());
    }

}
