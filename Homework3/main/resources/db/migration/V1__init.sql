create table categories
(
    id      bigserial primary key,
    title   varchar(255)
);

create table units
(
    id      bigserial primary key,
    title   varchar(255)
);

create table products
(
    id            bigserial primary key,
    category_id   bigserial,
    title         varchar(255),
    unit_id       bigserial,
    price         int,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp
);



insert into categories (title)
values ('ХБИ'), ('Молочка'), ('Яйцо'), ('Рыба'), ('Мясо');

insert into units (title)
values ('бух.'), ('шт.'), ('л'), ('кг'), ('дес. шт.');


insert into products (category_id, title, unit_id, price)
values
    (1, 'Хлеб', 1, 69),
    (1, 'Батон', 1, 76),
    (1, 'Хало', 1, 86),
    (1, 'Крендель', 2, 46),
    (2, 'Молоко', 3, 66),
    (2, 'Кефир', 3, 70),
    (2, 'Сливки', 3, 120),
    (2, 'Масло', 4, 520),
    (2, 'Сметана', 4, 120),
    (2, 'Сыр', 4, 585),
    (3, 'Яйцо куриное', 5,  60),
    (3, 'Яйцо перепелиное', 5,  80),
    (3, 'Яйцо страусиное', 2,  1500),
    (4, 'Сёмга',4,  1200),
    (4, 'Форель',4,  1350),
    (4, 'Скумбрия',4,  830),
    (4, 'Раки',4,  580),
    (5, 'Курятина',4,  151),
    (5, 'Индюшатина',4, 165),
    (5, 'Говядина',4,  325),
    (5, 'Свинина',4,   285);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (user_id, total_price, address, phone)
values (1, 200, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);









