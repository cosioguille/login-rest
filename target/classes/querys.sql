drop table roles;
drop table users;

create table roles(
role_id int not null auto_increment,
role_name varchar(128) not null,
primary key (role_id)
);

create table users(
user_id int not null auto_increment,
username varchar(128) not null,
password varchar(128) not null,
rol_id int not null,
primary key (user_id),
foreign key (rol_id) REFERENCES roles(role_id)
);