# --- !Ups
insert into line(id, name) values(1, 'Red');
insert into line(id, name) values(2, 'Blue');

/*Line A*/
insert into station_stop(id, name, line) values(1,'York','Red');
insert into station_stop(id, name, line) values(2,'Darlington','Red');
insert into station_stop(id, name, line) values(3,'Newcastle','Red');
insert into station_stop(id, name, line) values(4,'Berwick-upon-Tweed','Red');
insert into station_stop(id, name, line) values(5,'Dunbar','Red');
insert into station_stop(id, name, line) values(6,'Edinburgh Waverley','Red');
insert into station_stop(id, name, line) values(7,'Edinburgh Haymarket','Red');
insert into station_stop(id, name, line) values(8,'Stirling','Red');
insert into station_stop(id, name, line) values(9,'Perth','Red');
insert into station_stop(id, name, line) values(10,'Pitlochry','Red');
insert into station_stop(id, name, line) values(11,'Kingussie','Red');
insert into station_stop(id, name, line) values(12,'Aviemore','Red');
insert into station_stop(id, name, line) values(13,'Inverkeithing','Red');
insert into station_stop(id, name, line) values(14,'Leuchars','Red');
insert into station_stop(id, name, line) values(15,'Dundee','Red');
insert into station_stop(id, name, line) values(16,'Montrose','Red');
insert into station_stop(id, name, line) values(17,'Aberdeen','Red');

/*Line B*/
insert into station_stop(id, name, line) values(18,'York','Blue');
insert into station_stop(id, name, line) values(19,'Northallerton','Blue');
insert into station_stop(id, name, line) values(20,'Darlington','Blue');
insert into station_stop(id, name, line) values(21,'Durham','Blue');
insert into station_stop(id, name, line) values(22,'Newcastle','Blue');
insert into station_stop(id, name, line) values(23,'Sunderland','Blue');
insert into station_stop(id, name, line) values(24,'Morpeth','Blue');
insert into station_stop(id, name, line) values(25,'Alnmouth','Blue');
insert into station_stop(id, name, line) values(26,'Edinburgh Waverley','Blue');
insert into station_stop(id, name, line) values(27,'Edinburgh Haymarket','Blue');
insert into station_stop(id, name, line) values(28,'Motherwell','Blue');
insert into station_stop(id, name, line) values(29,'Glasgow','Blue');

/*Connections*/
/*Line A*/
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (1, 1, 2, 39);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (2, 2, 1, 39);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (3,2,3,28);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (4,3,2,28);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (5,3,4,41);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (6,4,3,41);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (7,4,5,22);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (8,5,4,22);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (9,5,6,24);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (10,6,5,24);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (11,6,7,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (12,7,6,5);


/*Line A Left of Fork*/
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (13,7,8,80);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (14,8,7,80);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (15,8,9,34);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (16,9,8,34);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (17,9,10,31);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (18,10,9,31);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (19,10,11,45);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (20,11,10,45);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (21,11,12,11);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (22,12,11,11);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (23,12,13,43);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (24,13,12,43);


insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (25,6,13,22);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (26,13,6,22);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (27,13,14,40);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (28,14,13,40);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (29,14,15,13);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (30,15,14,13);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (31,15,16,30);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (32,16,15,30);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (33,16,17,38);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (34,17,16,38);


/*Line A Right of Fork*/
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (35,18,19,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (36,19,18,20);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (37,19,20,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (38,20,19,20);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (39,20,21,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (40,21,20,15);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (41,21,22,14);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (42,22,21,14);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (43,22,23,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (44,23,22,20);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (45,22,24,12);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (46,24,22,12);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (47,24,25,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (48,25,24,15);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (49,25,26,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (50,26,25,15);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (51,26,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (52,27,26,5);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (53,27,28,45);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (54,28,27,45);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (55,28,29,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (56,29,28,20);

/*Line Connections*/
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (57,1,18,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (58,18,1,5);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (59,2,20,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (60,20,22,5);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (61,3,22,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (62,22,3,5);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (63,6,26,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (64,26,2,5);

insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (65,7,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (66,27,7,5);

# --- !Downs