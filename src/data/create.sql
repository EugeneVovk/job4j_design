create table roles(
	id serial primary key,
	name varchar(255)
);
create table users(
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id)
);
create table rules(
	id serial primary key,
	name text
);
create table category(
	id serial primary key,
	name varchar(255)
);
create table states(
	id serial primary key,
	name varchar(255)
);
create table item(
	id serial primary key,
	name text,
	users_id int references users(id),
	category_id int references category(id),
	states_id int references states(id)
);
create table comments(
	id serial primary key,
	name text,
	item_id int references item(id)
);
create table attachs(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);
create table role_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);
