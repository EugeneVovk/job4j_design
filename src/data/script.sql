create table books(
	book_id serial primary key,
	book_name varchar(255),
	author varchar(255),
	isAvailable boolean,
	pieces numeric
);
insert into books(book_name, author, isavailable, pieces) values(
'Зов Ктулху', 'Говард Филипс Лавкрафт', true, 2);
update books set 
isavailable = false,
pieces = 0;
delete from books;
select * from books;

