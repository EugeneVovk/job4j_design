create table departments(
	id serial primary key,
	name varchar(255)
);

create table emploers(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments(name) values
('IT'), ('Sales'), ('Marketing'), ('Training');

insert into emploers(name, departments_id) values
('Alex', 1), ('Ann', 1), ('Kate', 1), ('Boris', 2), ('Mark', 3);

--Выполнить запросы с left, rigth, full, cross соединениями
select d.name departments, e.name emploers 
from departments d 
left join emploers e 
on departments_id=d.id;

select d.name departments, e.name emploers 
from departments d 
right join emploers e 
on departments_id=d.id;

select d.name departments, e.name emploers 
from departments d 
full join emploers e 
on departments_id=d.id;

select d.name departments, e.name emploers 
from departments d 
cross join emploers e;

--Используя left join найти департаменты, у которых нет работников
select d.name departments, e.name emploers 
from departments d 
left join emploers e 
on departments_id=d.id 
where e.name is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат 
--(порядок вывода колонок в эти запросах также должен быть идентичный). 
select e.name emploers, d.name departments 
from emploers e 
right join departments d
on departments_id=d.id;

select e.name emploers, d.name departments
from departments d 
left join emploers e 
on departments_id=d.id;

--Создать таблицу teens с атрибутами name, gender и заполнить ее. 
--Используя cross join составить все возможные разнополые пары
create table teens(
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values
('Alex', 'male'), ('Boris', 'male'), ('Kate', 'female'), ('Olga', 'female');

select m.name, f.name 
from teens m 
cross join teens f 
where m.gender != f.gender;
