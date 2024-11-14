drop database if exists db_contacts;
create database if not exists db_contacts;
use db_contacts;

drop table if exists address;

create table if not exists address (
    pkAddress int auto_increment primary key,
    codeAddress varchar(12) not null,
    streetAddress varchar(255) not null,
    districtAddress varchar(255) not null,
    cityAddress varchar(255) not null,
    countryAddress varchar(255) not null,
    isWorkAddress boolean not null
);

drop table if exists contacts;
create table if not exists contacts (
    pkContact int auto_increment primary key,
    nameContact varchar(255) not null,
    dateBirthContact date null,
    nicknameContact varchar(30) null
);

drop table if exists address_contact;
create table if not exists address_contact (
    pkAddressContact int not null auto_increment primary key,
    fkContact int not null,
    fkAddress int not null,
    foreign key (fkAddress) references address (pkAddress),
    foreign key (fkContact) references contacts (pkContact)
);

drop table if exists mail;
create table if not exists mail (
    pkMail int auto_increment primary key,
    mail varchar(255) not null,
    tagMail varchar(255) null,
    fkContact int not null,
    unique (mail),
    foreign key (fkContact) references contacts (pkContact)
);

drop table if exists phonenumber;
create table if not exists phonenumber (
    pkPhoneNumber int auto_increment primary key,
    phoneNumber varchar(15) not null unique,
    tagPhone varchar(255) null,
    fkContact int not null,
    foreign key (fkContact) references contacts (pkContact)
);

drop table if exists user;
create table if not exists user (
    pkUser int auto_increment primary key,
    nameUser varchar(255) not null,
    mailUser varchar(255) not null unique,
    passwordUser varchar(255) not null unique
);

drop table if exists permission;
create table if not exists permission (
    pkPermission int auto_increment primary key,
    namePermission varchar(255) not null unique
);

drop table if exists user_permission;
create table if not exists user_permission (
    pkUserRule int auto_increment primary key,
    fkUser int not null,
    fkPermission int not null,
    foreign key (fkUser) references user (pkUser),
    foreign key (fkPermission) references permission (pkPermission)
);

drop table if exists recover_account;
create table recover_account (
    pkRecoverAccount int auto_increment primary key,
    expirationDate datetime(6) not null,
    hashSecurity varchar(32) not null,
    mailUser varchar(255) not null,
    nameUser varchar(255) not null
);

insert into permission (namePermission)
values ('CREATE'),
       ('READ'),
       ('ADMIN');