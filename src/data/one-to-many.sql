create table taxi_company(
	id serial primary key,
	name varchar(255),
	taxi_cab int
);
create table driver(
	id serial primary key,
	name varchar(255),
	taxi_company_id int references taxi_company(id)
);
insert into taxi_company(name, taxi_cab) values ('Yellow cars', 1024);
insert into driver(name, taxi_company_id) values ('Bob', 1);

select * from taxi_company where id in (select id from driver);
