# --- !Ups
/*
Line Table for Providing Consistent 'Line' input when creating Stations.
*/
create table line (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  constraint pk_line primary key (id)
);

/*
Stop Table for creating Stops. Line column may change to reference Line table.
*/
create table station_stop (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  line                          varchar(255),
  constraint pk_station_stop primary key (id)
);

/*
Connections Table holding information about connections. Each entity is
a unidirectional Connection.
*/
create table stop_connection (
  id                            bigint auto_increment not null,
  stop_a_id                     bigint not null,
  stop_b_id                     bigint not null,
  distance                      bigint not null,
  constraint pk_stop_connection primary key (id)
);

/*
Users table for accounts used when authenticating administrator actions.
*/
create table user (
  id                            bigint auto_increment not null,
  auth_token                    varchar(255),
  email_address                 varchar(256) not null,
  sha_password                  varbinary(64) not null,
  creation_date                 timestamp not null,
  full_name                     varchar(256) not null,
  constraint uq_user_email_address unique (email_address),
  constraint pk_user primary key (id)
);

alter table stop_connection add constraint fk_stop_connection_stop_a_id foreign key (stop_a_id) references station_stop (id) on delete cascade on update cascade;
create index ix_stop_connection_stop_a_id on stop_connection (stop_a_id);

alter table stop_connection add constraint fk_stop_connection_stop_b_id foreign key (stop_b_id) references station_stop (id) on delete cascade on update cascade;
create index ix_stop_connection_stop_b_id on stop_connection (stop_b_id);


# --- !Downs

alter table stop_connection drop constraint if exists fk_stop_connection_stop_a_id;
drop index if exists ix_stop_connection_stop_a_id;

alter table stop_connection drop constraint if exists fk_stop_connection_stop_b_id;
drop index if exists ix_stop_connection_stop_b_id;

drop table if exists line;

drop table if exists station_stop;

drop table if exists stop_connection;

drop table if exists user;

