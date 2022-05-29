CREATE TABLE IF NOT EXISTS TACO_ORDER (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists TACO (
    id identity,
    name varchar(50) not null,
    taco_order bigint not null,
    taco_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists INGREDIENT_REF (
    ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null
);

create table if not exists INGREDIENT (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

alter table TACO add foreign key (taco_order) references TACO_ORDER(id);

alter table INGREDIENT_REF add foreign key (ingredient) references INGREDIENT(id);