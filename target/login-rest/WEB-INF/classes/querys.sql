drop table user_rol;
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
role_id int not null,
primary key (user_id, role_id),
foreign key (user_id) references users(user_id),
foreign key (role_id) references roles(role_id)
);

insert into roles (role_name) values ("ADMIN");
insert into roles (role_name) values ("STANDARD");

insert into users (username, password) values ("admin", "12345678");
insert into users (username, password) values ("stand", "12345678");

insert into user_rol values (1, 1);
insert into user_rol values (1, 2);
insert into user_rol values (2, 1);