create table _task (
       id binary(16) not null,
        concluded bit not null,
        created_at datetime(6),
        deadline datetime(6) not null,
        task varchar(255) not null,
        primary key (id)
);