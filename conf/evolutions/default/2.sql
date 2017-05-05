# --- !Ups
insert into line(id, name) values(1, 'East Coast');
insert into line(id, name) values(2, 'Cross Country');

/*Line A*/
insert into station_stop(id, name, line) values(1,'York','East Coast');
insert into station_stop(id, name, line) values(2,'Darlington','East Coast');
insert into station_stop(id, name, line) values(3,'Newcastle','East Coast');
insert into station_stop(id, name, line) values(4,'Berwick Upon Tweed','East Coast');
insert into station_stop(id, name, line) values(5,'Dunbar','East Coast');
insert into station_stop(id, name, line) values(6,'Edinburgh Waverley','East Coast');
insert into station_stop(id, name, line) values(7,'Edinburgh Haymarket','East Coast');

insert into station_stop(id, name, line) values(8,'Stirling','East Coast');
insert into station_stop(id, name, line) values(9,'Perth','East Coast');
insert into station_stop(id, name, line) values(10,'Pitlochry','East Coast');
insert into station_stop(id, name, line) values(11,'Kingussie','East Coast');
insert into station_stop(id, name, line) values(12,'Aviemore','East Coast');

insert into station_stop(id, name, line) values(13,'Inverkeithing','East Coast');
insert into station_stop(id, name, line) values(14,'Leuchars','East Coast');
insert into station_stop(id, name, line) values(15,'Dundee','East Coast');
insert into station_stop(id, name, line) values(16,'Montrose','East Coast');
insert into station_stop(id, name, line) values(17,'Aberdeen','East Coast');

/*Line B*/
insert into station_stop(id, name, line) values(18,'York','Cross Country');
insert into station_stop(id, name, line) values(19,'Northallerton','Cross Country');
insert into station_stop(id, name, line) values(20,'Darlington','Cross Country');
insert into station_stop(id, name, line) values(21,'Durham','Cross Country');
insert into station_stop(id, name, line) values(22,'Newcastle','Cross Country');
insert into station_stop(id, name, line) values(23,'Sunderland','Cross Country');
insert into station_stop(id, name, line) values(24,'Morpeth','Cross Country');
insert into station_stop(id, name, line) values(25,'Alnmouth','Cross Country');
insert into station_stop(id, name, line) values(26,'Edinburgh Waverley','Cross Country');
insert into station_stop(id, name, line) values(27,'Edinburgh Haymarket','Cross Country');
insert into station_stop(id, name, line) values(28,'Motherwell','Cross Country');
insert into station_stop(id, name, line) values(29,'Glasgow','Cross Country');

/*Connections*/
/*Line A*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (1, 1, 2, 39);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (2, 2, 1, 39);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (3,2,3,28);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (4,3,2,28);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (5,3,4,41);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (6,4,3,41);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (7,4,5,22);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (8,5,4,22);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (9,5,6,24);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (10,6,5,24);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (11,6,7,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (12,7,6,5);


/*Line A Left of Fork*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (13,7,8,80);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (14,8,7,80);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (15,8,9,34);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (16,9,8,34);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (17,9,10,31);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (18,10,9,31);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (19,10,11,45);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (20,11,10,45);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (21,11,12,11);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (22,12,11,11);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (23,12,13,43);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (24,13,12,43);


insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (25,6,13,22);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (26,13,6,22);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (27,13,14,40);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (28,14,13,40);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (29,14,15,13);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (30,15,14,13);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (31,15,16,30);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (32,16,15,30);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (33,16,17,38);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (34,17,16,38);


/*Line A Right of Fork*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (35,18,19,20);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (36,19,18,20);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (37,19,20,20);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (38,20,19,20);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (39,20,21,15);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (40,21,20,15);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (41,21,22,14);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (42,22,21,14);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (43,22,23,20);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (44,23,22,20);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (45,22,24,12);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (46,24,22,12);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (47,24,25,15);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (48,25,24,15);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (49,25,26,15);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (50,26,25,15);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (51,26,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (52,27,26,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (53,27,28,45);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (54,28,27,45);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (55,28,29,20);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (56,29,28,20);

/*Line Connections*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (57,1,18,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (58,18,1,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (59,2,20,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (60,20,2,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (61,3,22,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (62,22,3,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (63,6,26,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (64,26,6,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (65,7,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (66,27,7,5);

# --- !Downs