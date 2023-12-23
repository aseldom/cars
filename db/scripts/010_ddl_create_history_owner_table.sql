create table history_owner(
    id serial primary key,
    car_id int references car(id),
    owner_id int references owners(id),
    startAt TImestamp,
    endAt TImestamp
);