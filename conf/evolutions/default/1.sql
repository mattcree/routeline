# --- !Ups
create table Stop(
id      long not null auto_increment,
name    varchar(255),
line    varchar(255),
constraint pk_stop primary key(id)
);


create sequence stop_seq;


# --- !Downs

set referential_integrity true;

drop table if exists Stop;