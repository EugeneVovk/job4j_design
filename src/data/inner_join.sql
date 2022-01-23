create table department(
	id serial primary key,
	name varchar(255)
);
create table employee(
	id serial primary key,
	name varchar(255),
	department_id int references department(id)
);
insert into department(name) values('IT');
insert into department(name) values('Sales');
insert into department(name) values('Managers');
insert into department(name) values('HR');
insert into department(name) values('Accountancy');

insert into employee(name, department_id) values('Alex', 1);
insert into employee(name, department_id) values('Ivan', 2);
insert into employee(name, department_id) values('Kseniya', 1);
insert into employee(name, department_id) values('Anton', 3);
insert into employee(name, department_id) values('Anna', 5);

select * from employee join department d on department_id=d.id;
select e.name, d.name from employee e join department d on department_id=d.id;
select e.name as Сотрудники, d.name as Отдел from employee e join department d on department_id=d.id;