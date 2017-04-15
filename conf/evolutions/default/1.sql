# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table station_stop (
  id                        bigint not null,
  name                      varchar(255),
  line                      varchar(255),
  constraint pk_station_stop primary key (id))
;

create table stop_connection (
  id                        bigint not null,
  stop_a                    bigint,
  stop_b                    bigint,
  distance                  bigint,
  constraint pk_stop_connection primary key (id))
;

create sequence station_stop_seq;

create sequence stop_connection_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists station_stop;

drop table if exists stop_connection;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists station_stop_seq;

drop sequence if exists stop_connection_seq;

