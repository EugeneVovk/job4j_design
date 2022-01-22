create table driving_license(
	id serial primary key,
	license int,
	category varchar(1)
);
create table driver(
	id serial primary key,
	name varchar(255)
);
create table license_driver(
	id serial primary key,
	driving_license_id int references driving_license(id) unique,
	driver_id int references driver(id) unique
);
insert into driving_license(license, category) values(12345, 'B');
insert into driver(name, driving_license_id) values('John');
insert into license_driver(driving_license_id, driver_id) values(1, 1);


