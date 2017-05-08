# --- !Ups
insert into line(id, name) values(1, 'East Coast');
insert into line(id, name) values(2, 'Cross Country');
insert into line(id, name) values(3, 'Virgin Trains');
insert into line(id, name) values(4, 'TransPennine Express');
insert into line(id, name) values(5, 'ScotRail');

insert into station(id, name) values (1,'Aberdeen');
insert into station(id, name) values (2,'Alnmouth');
insert into station(id, name) values (3,'Aviemore');
insert into station(id, name) values (4,'Berwick Upon Tweed');
insert into station(id, name) values (5,'Darlington');
insert into station(id, name) values (6,'Dunbar');
insert into station(id, name) values (7,'Dundee');
insert into station(id, name) values (8,'Durham');
insert into station(id, name) values (9,'Edinburgh Haymarket');
insert into station(id, name) values (10,'Edinburgh Waverley');
insert into station(id, name) values (11,'Glasgow');
insert into station(id, name) values (12,'Inverkeithing');
insert into station(id, name) values (13,'Kingussie');
insert into station(id, name) values (14,'Leuchars');
insert into station(id, name) values (15,'Montrose');
insert into station(id, name) values (16,'Morpeth');
insert into station(id, name) values (17,'Motherwell');
insert into station(id, name) values (18,'Newcastle');
insert into station(id, name) values (19,'Northallerton');
insert into station(id, name) values (20,'Perth');
insert into station(id, name) values (21,'Pitlochry');
insert into station(id, name) values (22,'Stirling');
insert into station(id, name) values (24,'Sunderland');
insert into station(id, name) values (25,'Bristol');
insert into station(id, name) values (26,'Cambridge');
insert into station(id, name) values (27,'Cardiff');
insert into station(id, name) values (28,'Carlisle');
insert into station(id, name) values (29,'York');
insert into station(id, name) values (30,'Liverpool');
insert into station(id, name) values (31,'Plymouth');
insert into station(id, name) values (32,'London');
insert into station(id, name) values (33,'Winchester');
insert into station(id, name) values (34,'Manchester Airport');
insert into station(id, name) values (35,'Manchester Piccadilly');
insert into station(id, name) values (36,'Thirsk');
insert into station(id, name) values (37,'Thornaby');
insert into station(id, name) values (38,'Middlesbrough');



/*East Coast*/
insert into station_stop(id, name, line) values(1,'York','East Coast');
insert into station_stop(id, name, line) values(2,'Darlington','East Coast');
insert into station_stop(id, name, line) values(3,'Newcastle','East Coast');
insert into station_stop(id, name, line) values(4,'Berwick Upon Tweed','East Coast');
insert into station_stop(id, name, line) values(5,'Dunbar','East Coast');
insert into station_stop(id, name, line) values(6,'Edinburgh Waverley','East Coast');
insert into station_stop(id, name, line) values(7,'Edinburgh Haymarket','East Coast');

 /*Branches of East Coast*/
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

/*Cross Country*/
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

/*TransPennine Express*/
insert into station_stop(id, name, line) values(30,'Manchester Airport','TransPennine Express');
insert into station_stop(id, name, line) values(31,'Manchester Piccadilly','TransPennine Express');
insert into station_stop(id, name, line) values(32,'York','TransPennine Express');
insert into station_stop(id, name, line) values(33,'Thirsk','TransPennine Express');
insert into station_stop(id, name, line) values(34,'Northallerton','TransPennine Express');
insert into station_stop(id, name, line) values(35,'Yarm','TransPennine Express');
insert into station_stop(id, name, line) values(36,'Thornaby','TransPennine Express');
insert into station_stop(id, name, line) values(37,'Middlesbrough','TransPennine Express');


/*Connections*/
/*East Coast*/
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

/*East Coast Left of Fork*/
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


/*Cross Country*/
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

/*TransPennine Express*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (57,30,31,15);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (58,31,30,15);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (59,31,32,109);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (60,32,31,109);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (61,32,33,16);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (62,33,32,16);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (63,33,34,8);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (64,34,33,8);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (65,34,35,15);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (66,35,34,15);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (67,35,36,9);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (68,36,35,9);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (69,36,37,8);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (70,37,36,8);

/*Line Connections*/
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (71,1,18,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (72,18,1,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (73,2,20,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (74,20,2,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (75,3,22,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (76,22,3,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (77,6,26,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (78,26,6,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (79,7,27,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (80,27,7,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (81,18,32,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (82,32,18,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (83,1,32,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (84,32,1,5);

insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (85,19,34,5);
insert into stop_connection(id, stop_a_id, stop_b_id, time)
values (86,34,19,5);

# --- !Downs