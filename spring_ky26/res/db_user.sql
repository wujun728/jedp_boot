drop database if exists db_user;
create database db_user;
use db_user;
set names gbk;
drop table if exists t_user;
create table t_user(id int primary key auto_increment,username varchar(30) ,password varchar(30) ,state boolean default true);
insert into t_user(username,password,state) values ("����","abcabc",true),("����","abcabc",true),("������","abcabc",true);
select * from t_user;

