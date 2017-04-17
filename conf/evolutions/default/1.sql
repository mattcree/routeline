# --- !Ups
create table station_stop (
  id                        bigint not null,
  name                      varchar(255),
  line                      varchar(255),
  constraint pk_station_stop primary key (id))
;

create table stop_connection (
  id                        bigint not null,
  stop_a_id                 bigint,
  stop_b_id                 bigint,
  distance                  bigint,
  constraint pk_stop_connection primary key (id))
;

create table user (
  id                        bigint not null,
  username                  varchar(255),
  auth_token                varchar(255),
  constraint pk_user primary key (id))
;

create sequence station_stop_seq;

create sequence stop_connection_seq;

create sequence user_seq;

alter table stop_connection add constraint fk_stop_connection_stopA_1 foreign key (stop_a_id) references station_stop (id) on delete cascade on update cascade;
create index ix_stop_connection_stopA_1 on stop_connection (stop_a_id);
alter table stop_connection add constraint fk_stop_connection_stopB_2 foreign key (stop_b_id) references station_stop (id) on delete cascade on update cascade;
create index ix_stop_connection_stopB_2 on stop_connection (stop_b_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists station_stop;

drop table if exists stop_connection;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists station_stop_seq;

drop sequence if exists stop_connection_seq;

drop sequence if exists user_seq;

