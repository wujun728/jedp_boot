/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/10/19 16:44:50                          */
/*==============================================================*/


drop table if exists Admin;

drop table if exists BuyCar;

drop table if exists Good;

drop table if exists OrderList;

drop table if exists User;

/*==============================================================*/
/* Table: Admin                                                 */
/*==============================================================*/
create table Admin
(
   AdminNo              int not null,
   AdminName            varchar(20),
   AdminPass            varchar(20)
);

/*==============================================================*/
/* Table: BuyCar                                                */
/*==============================================================*/
create table BuyCar
(
   CarId                int not null auto_increment,
   UserId               int,
   TotalManey           char(10),
   primary key (CarId)
);

/*==============================================================*/
/* Table: Good                                                  */
/*==============================================================*/
create table Good
(
   GoodId               int not null auto_increment,
   GoodName             varchar(50),
   GoodNo               int,
   GoodPrice            float,
   GoodNum              int,
   primary key (GoodId)
);

/*==============================================================*/
/* Table: OrderList                                             */
/*==============================================================*/
create table OrderList
(
   OrderId              int not null auto_increment,
   GoodId               int,
   CarId                int,
   BuyNum               int,
   BuyState             int,
   BuyDesc              varchar(200),
   primary key (OrderId)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   UserId               int not null auto_increment,
   UserName             varchar(20),
   UserPass             varchar(20),
   primary key (UserId)
);

alter table BuyCar add constraint FK_Relationship_1 foreign key (UserId)
      references User (UserId) on delete restrict on update restrict;

alter table OrderList add constraint FK_Relationship_2 foreign key (GoodId)
      references Good (GoodId) on delete restrict on update restrict;

alter table OrderList add constraint FK_Relationship_3 foreign key (CarId)
      references BuyCar (CarId) on delete restrict on update restrict;

