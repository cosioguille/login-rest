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
primary key (user_id)
);

create table user_rol(
user_id int not null,
rol_id int not null,
primary key (user_id, rol_id),
foreign key (user_id) references users(user_id),
foreign key (rol_id) references users(rol_id)
);

insert into roles (role_name) values ("ADMIN");
insert into roles (role_name) values ("STANDARD");

insert into users (username, password, rol_id) values ("admin", "12345678");
insert into users (username, password, rol_id) values ("stand", "12345678");

insert into user_rol values (1, 1);
insert into user_rol values (1, 2);
insert into user_rol values (2, 1);