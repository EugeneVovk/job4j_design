insert into roles(name) values ('Fixik');
insert into roles(name) values ('Losyash');
insert into roles(name) values ('Luntik');

insert into users(name, roles_id) values ('Ivan', 3);
insert into users(name, roles_id) values ('Zoya', 1);
insert into users(name, roles_id) values ('Alex', 2);

insert into rules(name) values ('Play');
insert into rules(name) values ('Create');
insert into rules(name) values ('Dream');

insert into category(name) values ('Dreamers');
insert into category(name) values ('Actors');

insert into states(name) values ('is_active');
insert into states(name) values ('isnt_active');

insert into item(name, users_id, category_id, states_id) 
	values('application for participation', 3, 1, 1);
insert into item(name, users_id, category_id, states_id) 
	values('approved', 1, 2, 2);
insert into item(name, users_id, category_id, states_id) 
	values('approved', 2, 2, 1);

insert into comments(name, item_id) values('Like', 1);
insert into comments(name, item_id) values('Dont_like', 1);
insert into comments(name, item_id) values('Ok', 2);

insert into attachs(name, item_id) values('file1', 2);
insert into attachs(name, item_id) values('file2', 2);
insert into attachs(name, item_id) values('file3', 1);

insert into role_rules(roles_id, rules_id) values (1, 1);
insert into role_rules(roles_id, rules_id) values (1, 2);
insert into role_rules(roles_id, rules_id) values (2, 1);
insert into role_rules(roles_id, rules_id) values (2, 2);
insert into role_rules(roles_id, rules_id) values (2, 3);
insert into role_rules(roles_id, rules_id) values (3, 1);