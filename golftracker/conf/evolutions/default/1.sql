# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_course primary key (id))
;

create table score (
  id                        bigint not null,
  date                      varchar(255),
  golfer_email              varchar(255),
  course_id                 bigint,
  constraint pk_score primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;


create table course_user (
  course_id                      bigint not null,
  user_email                     varchar(255) not null,
  constraint pk_course_user primary key (course_id, user_email))
;
create sequence course_seq;

create sequence score_seq;

create sequence user_seq;

alter table score add constraint fk_score_golfer_1 foreign key (golfer_email) references user (email) on delete restrict on update restrict;
create index ix_score_golfer_1 on score (golfer_email);
alter table score add constraint fk_score_course_2 foreign key (course_id) references course (id) on delete restrict on update restrict;
create index ix_score_course_2 on score (course_id);



alter table course_user add constraint fk_course_user_course_01 foreign key (course_id) references course (id) on delete restrict on update restrict;

alter table course_user add constraint fk_course_user_user_02 foreign key (user_email) references user (email) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists course;

drop table if exists course_user;

drop table if exists score;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists course_seq;

drop sequence if exists score_seq;

drop sequence if exists user_seq;

