package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

public class Util {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private final CrudRepository crudRepository = new CrudRepository(sf);

    private final CarBodyRepository carBodyRepository = new HibernateCarBodyRepository(crudRepository);
    private final ColorRepository colorRepository = new HibernateColorRepository(crudRepository);
    private final TransmissionRepository transmissionRepository = new HibernateTransmissionRepository(crudRepository);
    private final WheelDriveRepository wheelDriveRepository = new HibernateWheelDriveRepository(crudRepository);
    private final EngineTypeRepository engineTypeRepository = new HibernateEngineTypeRepository(crudRepository);

    public final EngineRepository engineRepository = new HibernateEngineRepository(crudRepository);
    public final HistoryOwnerRepository historyOwnerRepository = new HibernateHistoryOwnerRepository(crudRepository);
    public final PostRepository postRepository = new HibernatePostRepository(crudRepository);
    public final OwnerRepository ownerRepository = new HibernateOwnerRepository(crudRepository);
    public final UserRepository userRepository = new HibernateUserRepository(crudRepository);
    public final CarRepository carRepository = new HibernateCarRepository(crudRepository);
    public final PhotoRepository photoRepository = new HibernatePhotoRepository(crudRepository);
    public final PriceHistoryRepository priceHistoryRepository = new HibernatePriceHistoryRepository(crudRepository);

    public void clearRepository() {

        for (var e : historyOwnerRepository.findAll()) {
            historyOwnerRepository.deleteById(e.getId());
        }

        for (var e : ownerRepository.findAll()) {
            ownerRepository.deleteById(e.getId());
        }

        for (var e : priceHistoryRepository.findAll()) {
            priceHistoryRepository.deleteById(e.getId());
        }

        for (var e : photoRepository.findAll()) {
            photoRepository.deleteById(e.getId());
        }

        for (var e : postRepository.findAll()) {
            postRepository.deleteById(e.getId());
        }
        for (var e : userRepository.findAllOrderById()) {
            userRepository.delete(e.getId());
        }

        for (var e : carRepository.findAll()) {
            carRepository.deleteById(e.getId());
        }

        for (var e : engineRepository.findAll()) {
            engineRepository.deleteById(e.getId());
        }
    }

    public User getUser(String login) {
        User user = new User();
        user.setLogin(login);
        user.setPassword("password");
        user.setPhone("1111155555");
        return user;
    }

    public Post getPost(Car car, User user, String description) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(user);
        post.setCar(car);
        return post;
    }

    public Post getPost(Car car, User user, String description, Photo photo) {
        Post post = getPost(car, user, description);
        post.getPhotos().add(photo);
        photo.setPost(post);
        return post;
    }

    public PriceHistory getPriceHistory(Long before, Long after) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(before);
        priceHistory.setAfter(after);
        return priceHistory;
    }

    public Car getCar(String name, String vin) {
        Car car = new Car();
        car.setName(name);
        car.setMileage(100000);
        car.setVin(vin);
        car.setProductionYear(2000);
        car.setEngine(getEngine("Engine1"));
        car.setCarBody(getCarBody());
        car.setColor(getColor());
        car.setTransmission(getTransmission());
        car.setWheelDrive(getWheelDrive());
        return car;
    }

    public Photo getPhoto(String name, String path) {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setPath(path);
        return photo;
    }

    public Engine getEngine(String name) {
        Engine engine = new Engine();
        engine.setName(name);
        engine.setEngineValue(2.5);
        engine.setPower(100.0);
        engine.setEngineType(getEngineType());
        return engine;
    }

    public HistoryOwner getHistoryOwner(Car car, Owner owner) {
        HistoryOwner historyOwner = new HistoryOwner();
        historyOwner.setStartAt(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
        historyOwner.setEndAt(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
        historyOwner.setCar(car);
        historyOwner.setOwner(owner);
        return historyOwner;
    }

    public Owner getOwner(String name, User user) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setUser(user);
        return owner;
    }

    public EngineType getEngineType() {
        Collection<EngineType> engineTypes = engineTypeRepository.findAll();
        return engineTypes.stream().findFirst().get();
    }

    public Transmission getTransmission() {
        Collection<Transmission> transmissions = transmissionRepository.findAll();
        return transmissions.stream().findFirst().get();
    }

    public WheelDrive getWheelDrive() {
        Collection<WheelDrive> wheelDrives = wheelDriveRepository.findAll();
        return wheelDrives.stream().findFirst().get();
    }

    public CarBody getCarBody() {
        Collection<CarBody> carBodies = carBodyRepository.findAll();
        return carBodies.stream().findFirst().get();
    }

    public Color getColor() {
        Collection<Color> colors = colorRepository.findAll();
        return colors.stream().findFirst().get();
    }
}
