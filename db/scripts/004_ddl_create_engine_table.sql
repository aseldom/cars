create table engine(
    id serial primary key,
    name varchar not null,
    engine_value real not null,
    power real not null,
    engine_type_id int references engine_type(id)
);