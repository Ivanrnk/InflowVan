create table Mainreport
(waybill char(8),
shipping date,
van int,
manufacturer char(255),
provider char(255),
product varchar(255),
weight decimal (8,2),
location char(255),
registration date,
arrival date,
status boolean,
constraint key_field primary key (waybill,shipping,van)
);
