drop database if exists ihistory;
create database ihistory;
use ihistory;

create table if not exists postAssignment (
aID varchar(100) not null,
    primary key (aID)
);

create table if not exists requestExtension (
aID varchar(100) not null,
extensionReason varchar(100) not null,
submission varchar(100),
    primary key (aID)
);

create table if not exists extension (
aID varchar(100) not null,
extensionReason varchar(100) not null,
extension varchar(100) not null,
    primary key (aID)
);

drop database if exists shistory;
create database shistory;
use shistory;

create table if not exists postAssignment (
aID varchar(100) not null,
    primary key (aID)
);

create table if not exists postSubmission (
aID varchar(100) not null,
submission varchar(100) not null,
    primary key (aID)
);

create table if not exists requestExtension (
aID varchar(100) not null,
extensionReason varchar(100) not null,
submission varchar(100),
    primary key (aID)
);

create table if not exists extension (
aID varchar(100) not null,
extensionReason varchar(100) not null,
extension varchar(100) not null,
    primary key (aID)
);

create table if not exists postGrade (
aID varchar(100) not null,
submission varchar(100) not null,
tentativeGrade varchar(100) not null,
    primary key (aID)
);

create table if not exists acceptGrade (
aID varchar(100) not null,
tentativeGrade varchar(100) not null,
grade varchar(100) not null,
regradeReason varchar(100),
    primary key (aID)
);

create table if not exists requestRegrade (
aID varchar(100) not null,
tentativeGrade varchar(100) not null,
regradeReason varchar(100) not null,
grade varchar(100),
    primary key (aID)
);

create table if not exists regrade (
aID varchar(100) not null,
regradeReason varchar(100) not null,
grade varchar(100) not null,
    primary key (aID)
);

drop database if exists thistory;
create database thistory;
use thistory;

create table if not exists postSubmission (
aID varchar(100) not null,
submission varchar(100) not null,
    primary key (aID)
);

create table if not exists postGrade (
aID varchar(100) not null,
submission varchar(100) not null,
tentativeGrade varchar(100) not null,
    primary key (aID)
);

create table if not exists acceptGrade (
aID varchar(100) not null,
tentativeGrade varchar(100) not null,
grade varchar(100) not null,
regradeReason varchar(100),
    primary key (aID)
);

create table if not exists requestRegrade (
aID varchar(100) not null,
tentativeGrade varchar(100) not null,
regradeReason varchar(100) not null,
grade varchar(100),
    primary key (aID)
);

create table if not exists regrade (
aID varchar(100) not null,
regradeReason varchar(100) not null,
grade varchar(100) not null,
    primary key (aID)
);

