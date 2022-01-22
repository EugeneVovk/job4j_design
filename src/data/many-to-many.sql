create table driver(
	id serial primary key,
	name varchar(255),
	license int
);
create table taxi_cab(
	id serial primary key,
	name varchar(255),
	color varchar(255)
);
create table taxi_company(
	id serial primary key,
	driver_id int references driver(id),
	taxi_cab_id int references taxi_cab(id)
);
insert into driver(name, license) values ('Piter', 12345);
insert into taxi_cab(name, color) values ('childrens_cab', 'pink');
insert into taxi_company(driver_id, taxi_cab_id) values (1, 1);

insert into driver(name, license) values ('Karl', 12300);
insert into driver(name, license) values ('Ann', 12321);
insert into taxi_cab(name, color) values ('comfort', 'yellow');
insert into taxi_cab(name, color) values ('Business', 'black');
insert into taxi_company(driver_id, taxi_cab_id) values (1, 2);
insert into taxi_company(driver_id, taxi_cab_id) values (1, 3);
insert into taxi_company(driver_id, taxi_cab_id) values (2, 1);
insert into taxi_company(driver_id, taxi_cab_id) values (2, 2);
insert into taxi_company(driver_id, taxi_cab_id) values (2, 3);
insert into taxi_company(driver_id, taxi_cab_id) values (3, 1);
insert into taxi_company(driver_id, taxi_cab_id) values (3, 2);
insert into taxi_company(driver_id, taxi_cab_id) values (3, 3);