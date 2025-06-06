create schema if not exists demo;

USE demo;

create table demo.USERS
(
    id         bigint auto_increment primary key,
    username   varchar(50)                          not null,
    email      varchar(100)                         not null,
    password   varchar(255)                         not null,
    first_name varchar(50)                          null,
    last_name  varchar(50)                          null,
    created_at timestamp  default CURRENT_TIMESTAMP null,
    updated_at timestamp  default CURRENT_TIMESTAMP null,
    active     tinyint(1) default 1                 null,
    constraint email unique (email),
    constraint username unique (username)
);

-- UserEntity data
INSERT INTO demo.USERS (username, email, password, first_name, last_name)
VALUES ('john_doe', 'john.doe@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.ODEPvjG0FwF3zzf0.fD97.VaO3vLC1C', 'John',
        'Doe'),
       ('jane_smith', 'jane.smith@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.ODEPvjG0FwF3zzf0.fD97.VaO3vLC1C', 'Jane',
        'Smith'),
       ('admin_user', 'admin@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.ODEPvjG0FwF3zzf0.fD97.VaO3vLC1C', 'Admin',
        'UserEntity'),
       ('mary_johnson', 'mary.johnson@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.ODEPvjG0FwF3zzf0.fD97.VaO3vLC1C',
        'Mary', 'Johnson'),
       ('robert_brown', 'robert.brown@example.com', '$2a$10$xn3LI/AjqicFYZFruSwve.ODEPvjG0FwF3zzf0.fD97.VaO3vLC1C',
        'Robert', 'Brown');
