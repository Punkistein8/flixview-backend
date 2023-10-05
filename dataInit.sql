insert into plan (price_pla, type) values (14.25, 'Basic');
insert into plan (price_pla, type) values (29.5, 'Intermediate');
insert into plan (price_pla, type) values (40.25, 'Advanced');

insert into role (type) values ('Normal');
insert into role (type) values ('Admin');

insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age) values (null, 1, 'admin@a', '1234', 'Active', 16);