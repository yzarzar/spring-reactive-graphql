create table player
(
    id          serial primary key,
    name        varchar(255) not null,
    age         int,
    club        varchar(255) not null,
    nationality varchar(255) not null
);