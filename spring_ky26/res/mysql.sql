show databases;#�鿴ϵͳ�����е����ݿ�
drop database if exists db_sql;
create database db_sql;#�������ݿ�
select database(),verson(),now();
use db_sql;
set names gbk;#���ñ���
show tables;
drop table if exists t_food;
create table t_food(id int primary key auto_increment,name varchar(30) ,price int );
desc t_food;
#CRUD
insert into t_food(name,price) value ("ʵ��"��30);
insert into t_food(name,price) values ("ʵ��"��30),("ʵ��"��30);
delete from t_food where id=2;
update t_food set name="��ʳ" where id=2;
select * from t_food;
#\.·��-----ִ�����ݿ�ű��ļ�
#select *
#from ����
#where ����
#group by ����
#having ��������
#order by ����
# limit ��ҳ
#���ݿ��е�Լ��:"�����ܱ�֤"�������ݿ����������ȷ��
create table t_food(
id int primary key auto_increment,#����Լ��
name varchar(30) unique not null,#Ψһ ����
price int default '8888',#Ĭ��Լ��
birthday date,
createDatetime TIMSTAMP default current_timestamp
);
desc t_food;

alter table t_food add column remark varchar(2);

select id from t_food where id>2;
select id from t_food where id>2 and id<6;
select id from t_food where id between 1 and 6;#[1,6]
select id from t_food where in(1,6);

#ģ����ѯ
select name from t_food where name="zs";
select name from t_food where name like '';#%�������ַ�----��_��������һ���ַ�

#���ú���
select count(id),avg(id),max(id) from t_food;
#���
create table t_user(id int ,name varchar(30) , foreign key(id) references t_food(id) );