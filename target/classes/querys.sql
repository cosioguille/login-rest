drop table users;

create table users(
user_id int not null auto_increment,
username varchar(128) not null,
password varchar(128) not null,
primary key (user_id)
);