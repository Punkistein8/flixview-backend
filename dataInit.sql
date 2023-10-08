insert into plan (price_pla, type)
values (14.25, 'Basic');
insert into plan (price_pla, type)
values (29.5, 'Intermediate');
insert into plan (price_pla, type)
values (40.25, 'Advanced');

insert into role (type)
values ('Normal');
insert into role (type)
values ('Admin');

insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 1, 'a@a', '1234', 'Active', 21);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (3, 2, 'admin@a', '1234', 'Active', 16);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (1, 2, 'b@b', '1234', 'Active', 25);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (1, 2, 'c@c', '1234', 'Active', 32);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 2, 'd@d', '1234', 'Active', 16);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (3, 2, 'e@e', '1234', 'Active', 57);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 2, 'f@f', '1234', 'Active', 29);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (1, 2, 'g@g', '1234', 'Active', 12);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (1, 2, 'h@h', '1234', 'Active', 17);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 2, 'i@i', '1234', 'Active', 28);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (3, 2, 'j@j', '1234', 'Active', 46);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (3, 2, 'k@k', '1234', 'Active', 33);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 2, 'l@l', '1234', 'Active', 32);
insert into userflix (fk_id_pla, fk_id_rol, email, password, state_use, age)
values (2, 2, 'm@m', '1234', 'Active', 30);

update userflix
set fk_id_pla = 1
where id_use = 1;

select * from plan;
SELECt * from userflix;

SELECt count(*) from userflix;

SELECT count(*), plan.type from userflix
                                    inner join plan on userflix.fk_id_pla = plan.id
group by plan.type;

SELECT count(*) from profile;
