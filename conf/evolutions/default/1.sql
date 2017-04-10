# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table stop (
  key                       bigint not null,
  name                      varchar(255),
  line                      varchar(255),
  constraint pk_stop primary key (key))
;


create sequence stop_seq;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists stop;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists stop_seq;

