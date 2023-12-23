create table auto_user
(
    id serial primary key,
    name varchar,
    login varchar unique not null,
    password  varchar not null,
    phone varchar not null
);
