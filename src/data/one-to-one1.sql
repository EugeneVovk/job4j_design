create table driving_license(
	id serial primary key,
	license int,
	category varchar(1)
);
create table driver(
	id serial primary key,
	name varchar(255),
	driving_license_id int references driving_license(id) unique
);
insert into driving_license(license, category) values(12345, 'B');
insert into driver(name, driving_license_id) values('John', 1);
select * from driver;
select * from driving_license;
select * from driving_license where id in (select id from driver);

