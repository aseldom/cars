create table history_owner(
    id serial primary key,
    car_id int not null references car(id),
    owner_id int not null references owners(id),
    startAt TImestamp,
    endAt TImestamp
);