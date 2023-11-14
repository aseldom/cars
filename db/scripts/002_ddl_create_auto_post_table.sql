create table auto_post
(
    id                  serial primary key,
    description         varchar                         not null,
    start_time          timestamp                       not null,
    auto_user_id        int references auto_user (id)   not null
);
