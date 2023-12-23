create table auto_post
(
    id serial primary key,
    description varchar not null,
    created timestamp without time zone default now(),
    sales boolean not null,
    auto_user_id int references auto_user(id),
    car_id int references car(id)
);
