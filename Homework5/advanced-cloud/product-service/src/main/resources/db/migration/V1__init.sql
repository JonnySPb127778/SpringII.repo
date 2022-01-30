
create table products
(
    id            bigserial primary key,
    title         varchar(255),
    price         integer,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp
);


insert into products (title, price)
values
    ('Хлеб', 69),
    ('Батон', 76),
    ('Хало', 86),
    ('Крендель', 46),
    ('Молоко', 66),
    ('Кефир', 70),
    ('Сливки', 120),
    ('Масло', 520),
    ('Сметана', 120),
    ('Сыр', 585),
    ('Яйцо куриное', 60),
    ('Яйцо перепелиное', 500),
    ('Сёмга', 200),
    ('Форель',  1350),
    ('Скумбрия',  830),
    ('Раки',  580),
    ('Курятина',  151),
    ('Индюшатина', 165),
    ('Говядина',  325),
    ('Свинина',   285);







