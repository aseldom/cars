create table auto_post
(
    id serial primary key,
    description varchar not null,
    created timestamp without time zone default now(),
    auto_user_id int not null references auto_user(id),
    car_id int not null references car(id)
);
