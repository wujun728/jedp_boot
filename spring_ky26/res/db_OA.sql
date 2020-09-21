drop database if exists db_OA;
create database db_OA;
use db_OA;
set names gbk;
drop table if exists employee;
create table employee(id int primary key auto_increment,name varchar(20) ,loginName varchar(20),password varchar(20),groupId int);
insert into employee(name,loginName,password,groupId) values ("����","liubei","liubei",1),("�ŷ�","zhangfei","zhangfei",2),("����","guanyu","guanyu",2);
select * from employee;

drop table if exists loan;
create table loan(id int primary key auto_increment,employeeId int ,title varchar(100),amount double,applyDate datetime,status int);
insert into loan(employeeId,title,amount,applyDate,status) values (2,"������",1500.0000,20090803,1),(2,"������",5000.0000,20090804,2),(3,"�������",10000.0000,20090804,0),(1,"�д��ͻ����",1000.0000,20090805,0);
select * from loan;
alter table loan add constraint FK_ID foreign key(employeeId) REFERENCES employee(id);
alter table loan drop constraint FK_ID;
#select * from loan where employeeID=(select id from employee where loginName='zhangfei');
