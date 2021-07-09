----------------------------------------------------
---------------修改优化部分，待交付-----------------
--------------------20210709------------------------
新建表：
create table traindata_breathe as select * from traindata;
delete from traindata_breathe where type != 'breathe';

create table traindata_ecg as select * from traindata;
delete from traindata_ecg where type != 'ecg';

create table traindata_hr as select * from traindata;
delete from traindata_hr where type != 'hr';

create table traindata_power as select * from traindata;
delete from traindata_power where type != 'Power';

create table traindata_stride as select * from traindata;
delete from traindata_stride where type != 'StrideFrequency';

create table traindata_temp as select * from traindata;
delete from traindata_temp where type != 'temp';

新建索引
create index ids_traintask_tm1 on traintask(start_time);
create index ids_traindata_hr1 on traindata_hr(sn,traindate);
create index ids_traindata_hr2 on traindata_temp(sn,traindate);
create index ids_traindata_hr3 on traindata_breathe(sn,traindate);
create index ids_traindata_hr4 on traindata_power(sn,traindate);


my.ini配置修改
tmp_table_size=256M
max_heap_table_size=256M


show VARIABLES like 'tmp_table_size'; 
set GLOBAL tmp_table_size=629145600;
SHOW VARIABLES LIKE 'max_heap_table_size%'; 
SET GLOBAL max_heap_table_size=1073741824;
variables

问题SQL
select 
	t1.id trainId,
	t1.name trainName,
	t1.start_time, 
	t1.end_time,
	m.id memId, 
	m.name memName,
	m.sn memSn,
	m.heartrate,
	m.height,
	m.weight,
	round(avg(v1.value),0) hrval,
	max(v1.value) hrvalmax,
	round(avg(v2.value),0) tempval,
	round(avg(v3.value),0) breatheval,
	round(avg(ifnull(v1.value,0))/heartrate*100, 0) hrpercent, 
	max(v4.value) caloryval 
from traintask t1 
inner join member m on t1.team = m.group 
left join traindata_hr v1 on (v1.sn = m.sn and v1.trainDate > t1.start_time and (v1.trainDate < t1.end_time or t1.end_time is null)) 
left join traindata_temp v2 on (v2.sn = m.sn and v2.trainDate > t1.start_time and (v2.trainDate < t1.end_time or t1.end_time is null)) 
left join traindata_breathe v3 on (v3.sn = m.sn and v3.trainDate > t1.start_time and (v3.trainDate < t1.end_time or t1.end_time is null)) 
left join traindata_power v4 on (v4.sn = m.sn and v4.trainDate > t1.start_time and (v4.trainDate < t1.end_time or t1.end_time is null)) 
where t1.id=5 group by m.sn;




select 
	t1.id trainId,
	t1.name trainName,
	t1.start_time, 
	t1.end_time,
	m.id memId, 
	m.name memName,
	m.sn memSn,
	m.heartrate,
	m.height,
	m.weight,
	(select round(avg(v1.value),0) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate > t1.start_time and (v1.trainDate < t1.end_time or t1.end_time is null)) hrval,
	(select max(v1.value) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate > t1.start_time and (v1.trainDate < t1.end_time or t1.end_time is null)) hrvalmax,
	(select round(avg(v2.value),0) from traindata_temp v2 where v2.sn = m.sn and v2.trainDate > t1.start_time and (v2.trainDate < t1.end_time or t1.end_time is null)) tempval,
	(select round(avg(v3.value),0) from traindata_breathe v3 where v3.sn = m.sn and v3.trainDate > t1.start_time and (v3.trainDate < t1.end_time or t1.end_time is null)) breatheval,
	(select round(avg(ifnull(v1.value,0))/heartrate*100, 0) from traindata_hr v1 where v1.sn = m.sn and v1.trainDate > t1.start_time and (v1.trainDate < t1.end_time or t1.end_time is null)) hrpercent,
	(select max(v4.value) from traindata_power v4 where v4.sn = m.sn and v4.trainDate > t1.start_time and (v4.trainDate < t1.end_time or t1.end_time is null)) caloryval
from traintask t1 
inner join member m on t1.team = m.group 
where t1.id=5 group by m.sn;

