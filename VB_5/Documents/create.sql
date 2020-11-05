use study_buddy;

drop table if exists users;
create table users (
user_id int,
user_pwd varchar(20),
name varchar(30),
phone_no varchar(11),
email_add varchar (20),
primary key (user_id) 
);

drop table if exists user_friend;
create table user_friend(
user_id int,
friend_id int,
primary key (user_id),
foreign key (user_id) references users(user_id),
foreign key (friend_id) references users(user_id)

);

drop table if exists pet;
create table pet(
pet_id int,
pet_rank int,
pet_name varchar(30),
primary key (pet_id) 
);

drop table if exists users_pet;
create table users_pet (
user_id int, 
pet_id int,
primary key (user_id, pet_id),
foreign key (user_id) references users(user_id),
foreign key (pet_id) references pet(pet_id)
);

drop table if exists study_data;
create table study_date(
stud_id int,
user_id int,
stud_time time,
stud_end timestamp,
study_start timestamp,
primary key (stud_id),
foreign key (user_id) references users(user_id)
);

