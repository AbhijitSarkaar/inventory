create database inventory;
use inventory;


create table products(
	product_id bigint primary key auto_increment,
    product_name varchar(50) not null,
    product_description varchar(150) not null,
    sku varchar(25) not null,
    price decimal(10,2) not null,
    created_at timestamp default current_timestamp
);


create table users(
	user_id bigint primary key auto_increment,
    username varchar(50) not null,
    email varchar(50) not null,
    password varchar(200) not null
);

-- alter table users
-- modify column password varchar(200) not null;
-- desc users;


create table roles(
	role_id int primary key auto_increment,
    role_name varchar(50) not null
);
insert into roles(role_name) values 
	("ROLE_USER"), ("ROLE_ADMIN"); 


create table user_role(
	user_id bigint not null,
    role_id int not null
);


create table orders(
	order_id bigint primary key auto_increment,
    total_price decimal(10,2) not null,
    user_id bigint not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp,
    
    constraint fk_order_user
    foreign key (user_id)
    references users(user_id)
    on delete cascade
);

-- alter table orders
-- add column created_at timestamp default current_timestamp,
-- add column updated_at timestamp;


create table order_items(
	order_item_id bigint primary key auto_increment,
    unit_price decimal(10, 2) not null,
    quantity int not null,
    order_id bigint not null,
    product_id bigint not null,
    
    constraint fk_order_item_order
    foreign key (order_id)
    references orders(order_id)
    on delete cascade,
    
    constraint fk_order_item_product
    foreign key (product_id)
    references products(product_id)
    on delete cascade
);


create table skus(
	sku_id varchar(50) primary key,
    quantity int not null,
    version bigint not null default 1
);

insert into skus(sku_id, quantity) values 
	("qpqpqp111qpqpqpq", 5),
    ("qpqpqp222qpqpqpq", 5),
    ("qpqpqp333qpqpqpq", 5),
    ("qpqpqp444qpqpqpq", 5),
    ("qpqpqp555qpqpqpq", 5);


