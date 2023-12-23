create table car(

    id serial primary key,
    name varchar not null,
    vin varchar not null unique,
    production_year int not null,
    mileage int not null,
    engine_id int references engine(id),
    color_id int references color(id),
    transmission_id int references transmissions(id),
    wheel_drive_id int references wheel_drive(id),
    car_body_id int references car_body(id)

);