package ru.job4j.cars.mapper;

import org.mapstruct.Mapper;
import ru.job4j.cars.dto.PostCreateDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default PostDto getPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setCarModel(post.getCar().getName());
        postDto.setDescription(post.getDescription());
        postDto.setYear(post.getCar().getProductionYear());
        postDto.setMileage(post.getCar().getMileage());
        postDto.setEngine(post.getCar().getEngine().getNumber());
        postDto.setPhotos(post.getPhotos());
        postDto.setPrice(post.getPriceHistories().get(post.getPriceHistories().size() - 1).getAfter());
        postDto.setStatus(post.isSales());
        return postDto;
    }

    default Post getPostFromPostCreateDto(PostCreateDto postCreateDto) {
        Post post = new Post();
        post.setDescription(postCreateDto.getPostDescription());
        return post;
    }

    default Car getCarFromPostCreateDto(PostCreateDto postCreateDto) {
        Car car = new Car();
        car.setProductionYear(postCreateDto.getCarProductionYear());
        car.setName(postCreateDto.getCarName());
        car.setMileage(postCreateDto.getCarMileage());
        car.setVin(postCreateDto.getCarVin());
        car.setEngine(getEngineFromPostCreateDto(postCreateDto));
        return car;
    }

    default Engine getEngineFromPostCreateDto(PostCreateDto postCreateDto) {
        Engine engine = new Engine();
        engine.setNumber(postCreateDto.getEngineNumber());
        engine.setPower(postCreateDto.getEnginePower());
        engine.setEngineValue(postCreateDto.getEngineValue());
        return engine;
    }

    default HistoryOwner getHistoryOwnerFromPostCreateDto(PostCreateDto postCreateDto, Owner owner) {
        HistoryOwner historyOwner = new HistoryOwner();
        historyOwner.setOwner(owner);
        historyOwner.setStartAt(Timestamp.valueOf(postCreateDto.getCarOwnerStart().atStartOfDay()));
        return historyOwner;
    }
}
