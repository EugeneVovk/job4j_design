create table devices(
	id serial primary key,
	name varchar(255),
	price float
);
create table people(
	id serial primary key,
	name varchar(255)
);
create table devices_people(
	id serial primary key,
	devices_id int references devices(id),
	people_id int references people(id)
);
insert into devices(name, price) values('MacBook', 20000);
insert into devices(name, price) values('AirPods', 1500);
insert into devices(name, price) values('iPad', 5000);
insert into devices(name, price) values('Apple Watch', 3500);
insert into devices(name, price) values('iPhone', 10000);
insert into devices(name, price) values('iMac', 17000);
insert into devices(name, price) values('Magic Keyboard', 2700);

insert into people(name) values('Ann'), ('Alex'), ('Timur'), ('Oksana'), ('Kate'), ('Olga');

insert into devices_people(devices_id, people_id) values(5, 1);
insert into devices_people(devices_id, people_id) values(2, 1);
insert into devices_people(devices_id, people_id) values(5, 2);
insert into devices_people(devices_id, people_id) values(1, 6);
insert into devices_people(devices_id, people_id) values(2, 6);
insert into devices_people(devices_id, people_id) values(3, 6); 
insert into devices_people(devices_id, people_id) values(4, 6);
insert into devices_people(devices_id, people_id) values(5, 6);
insert into devices_people(devices_id, people_id) values(6, 6);
insert into devices_people(devices_id, people_id) values(7, 6);
insert into devices_people(devices_id, people_id) values(1, 5);
insert into devices_people(devices_id, people_id) values(2, 5);
insert into devices_people(devices_id, people_id) values(5, 5);
insert into devices_people(devices_id, people_id) values(1, 4);
insert into devices_people(devices_id, people_id) values(2, 4);
insert into devices_people(devices_id, people_id) values(5, 4);
insert into devices_people(devices_id, people_id) values(3, 3);

--Используя агрегатные функции вывести среднюю цену устройств.
select round(avg(price)) "Средняя цена устройств" from devices;

--Используя группировку вывести для каждого человека среднюю цену его устройств.
select p.name Имя, round(avg(price)) "Средняя цена устройств"
from people p
join devices_people on people_id = p.id
join devices d on devices_id = d.id
group by 1;

--Дополнить запрос - чтобы средняя стоимость устройств должна быть больше 5000.
select p.name Имя, round(avg(price)) "Средняя цена устройств"
from people p
join devices_people on people_id = p.id
join devices d on devices_id = d.id
group by p.name
having round(avg(price)) > 5000;

