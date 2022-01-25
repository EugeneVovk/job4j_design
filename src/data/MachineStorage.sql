create table Body(
	id serial primary key,
	type varchar(255)
);

create table Engine(
	id serial primary key,
	type varchar(255)
);

create table Transmission(
	id serial primary key,
	type varchar(255)
);

create table Car(
	id serial primary key,
	name varchar(255),
	body_id int references Body(id),
	engine_id int references Engine(id),
	transmission_id int references Transmission(id)
);

insert into Body(type) values
('sedan'), 
('coupe'), 
('hatchback'), 
('station wagon'), 
('minivan'),
('pickup truck');

insert into Engine(type) values
('petrol'), 
('diesel'), 
('electric'), 
('hybrid'),
('gaz');

insert into Transmission(type) values
('manual'), 
('automatic'), 
('variator'), 
('robotic'),
('hydromechanical');

insert into Car(name, body_id, engine_id, transmission_id) values
('Tesla', 1, 3, 2),
('Toyota', 5, 2, 1),
('Subaru', 1, 1, 3),
('BMW', 3, 3,  2),
('Mercedes-Benz', 2, 1, 2),
('Audi', 1, 1, 4),
('Porshe', 3, 4, 2),
('Volvo', 4, 1, 2);

--1) Вывести список всех машин и все привязанные к ним детали.
select c.name Машина, b.type Кузов, e.type Двигатель, t.type "Коробка передач" 
from car c
join body b on body_id=b.id
join engine e on engine_id=e.id
join transmission t on transmission_id=t.id;

--2) Вывести отдельно детали (1 деталь - 1 запрос), 
--	 которые не используются НИ в одной машине, кузова, двигатели, коробки передач.
select b.type Кузов, c.name Машина
from body b 
left join car c 
on body_id=b.id 
where c.name is null;

select e.type Двигатель, c.name Машина
from engine e 
left join car c 
on engine_id=e.id 
where c.name is null;

select t.type "Коробка передач", c.name Машина
from transmission t 
left join car c 
on transmission_id=t.id 
where c.name is null;
