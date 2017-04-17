# --- !Ups


insert into station_stop(id, name, line) values(1,'York','A');
insert into station_stop(id, name, line) values(2,'Darlington','A');
insert into station_stop(id, name, line) values(3,'Newcastle','A');
insert into station_stop(id, name, line) values(4,'Berwick-upon-Tweed','A');
insert into station_stop(id, name, line) values(5,'Dunbar','A');
insert into station_stop(id, name, line) values(6,'Edinburgh Waverley','A');
insert into station_stop(id, name, line) values(7,'Edinburgh Haymarket','A');
insert into station_stop(id, name, line) values(8,'Stirling','A');
insert into station_stop(id, name, line) values(9,'Perth','A');
insert into station_stop(id, name, line) values(10,'Pitlochry','A');
insert into station_stop(id, name, line) values(11,'Kingussie','A');
insert into station_stop(id, name, line) values(12,'Aviemore','A');
insert into station_stop(id, name, line) values(13,'Inverkeithing','A');
insert into station_stop(id, name, line) values(14,'Leuchars','A');
insert into station_stop(id, name, line) values(15,'Dundee','A');
insert into station_stop(id, name, line) values(16,'Montrose','A');
insert into station_stop(id, name, line) values(17,'Aberdeen','A');

/*Line B*/
insert into station_stop(id, name, line) values(18,'York','B');
insert into station_stop(id, name, line) values(19,'Northallerton','B');
insert into station_stop(id, name, line) values(20,'Darlington','B');
insert into station_stop(id, name, line) values(21,'Durham','B');
insert into station_stop(id, name, line) values(22,'Newcastle','B');
insert into station_stop(id, name, line) values(23,'Sunderland','B');
insert into station_stop(id, name, line) values(24,'Morpeth','B');
insert into station_stop(id, name, line) values(25,'Alnmouth','B');
insert into station_stop(id, name, line) values(26,'Edinburgh Waverley','B');
insert into station_stop(id, name, line) values(27,'Edinburgh Haymarket','B');
insert into station_stop(id, name, line) values(28,'Motherwell','B');
insert into station_stop(id, name, line) values(29,'Glasgow','B');

/*Connections
Line A*/
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (1, 1, 2, 39);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (2,2,3,28);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (3,3,4,41);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (4,4,5,22);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (5,5,6,24);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (6,6,7,5);

/* Left of Fork */
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (7,7,8,80);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (8,8,9,34);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (9,9,10,31);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (10,10,11,45);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (11,11,12,11);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (12,12,13,43);

/* Right of Fork */
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (13,6,13,22);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (14,13,14,40);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (15,14,15,13);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (16,15,16,30);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (17,16,17,38);


/* Line B */
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (18,18,19,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (19,19,20,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (20,20,21,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (21,21,22,14);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (22,22,23,20);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (23,22,24,12);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (24,24,25,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (25,25,26,15);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (26,26,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (27,27,28,45);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (28,28,29,20);

/* Connections between lines */
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (29,1,18,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (30,2,20,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (31,3,22,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (32,6,26,5);
insert into stop_connection(id, stop_a_id, stop_b_id, distance)
values (33,7,27,5);

# --- !Downs