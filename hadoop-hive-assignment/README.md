Assignment/Practice - HIVE
========

#### => Install HIVE
$sudo yum install hive

#### => Pre-requisite before using hive
$sudo -u hdfs hadoop fs -mkdir /user/hive/warehouse

$hadoop fs -chmod g+w /tmp

$sudo -u hdfs hadoop fs -chmod g+w /user/hive/warehouse

$sudo -u hdfs hadoop fs -chown -R training /user/hive/warehouse

$sudo chmod 777 /var/lib/hive/metastore

#### => Go to HIVE shell
$hive

#### => Create table u_data
hive> CREATE TABLE u_data (user_id INT, movieid INT, rating INT, unixtime STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
DESCRIBE u_data;

#### => Load data into u_data table from file system
hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/ml-100k/u.data' OVERWRITE INTO TABLE u_data;

#### => Create table u_user
hive> CREATE TABLE u_user (userid INT, age INT, gender STRING, occupation STRING, zipcode STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '|' STORED AS TEXTFILE;

#### => Load data into u_user table from file system
hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/ml-100k/u.user' OVERWRITE INTO TABLE u_user;

#### => Example of FULL OUTER JOIN using u_user and u_data table
hive> SELECT UD.*, U.occupation, U.gender, U.age FROM u_user U FULL OUTER JOIN u_data UD on (U.userid = UD.user_id) LIMIT 10;

#### => Query to count number of MALE/FEMALE user
hive> SELECT U.gender, COUNT(DISTINCT U.userid) FROM u_user U GROUP By U.gender;

#### => Create table u_gender_sum
hive> CREATE TABLE u_gender_sum (gender string, count int) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
 
#### => Populate u_gender table from existing table
hive> FROM u_user INSERT OVERWRITE TABLE u_gender_sum SELECT u_user.gender, count(DISTINCT u_user.userid) GROUP BY u_user.gender;


Assignment-1
========

Hive Basics Assignment:

1. Use driver_data.zip for this assignment

2. Extract the zip file, it has 3 files drivers, timesheet, truck_event_text_partition

3. Create table driver based on the headers provided in drivers file and remove headers from drivers file, repeat same with timesheet and truck_event_text_partition

4. Load data into created tables

5. Join timesheet and drivers and get total time logged per driver along with his name.

6. Join 3 tables using a join statement and group by driver id and count rows

__Note__ : Try this exercise with both managed and external tables. Drop these tables and see what is happening in HDFS at /user/hive/warehouse.

Managed Tables
===

#### => Create managed tables (drivers, timesheet & truck_event based on CSV files)
hive> CREATE TABLE drivers (driverId INT, name STRING, ssn STRING, loc STRING, certified STRING, wagePlan STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

hive> CREATE TABLE timesheet (driverId INT, week INT, hoursLogged INT, mileLogged INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

hive> CREATE TABLE truck_event (driverId INT, truckId INT, eventTime STRING, eventType STRING, longitude DOUBLE, latitude DOUBLE, eventKey STRING, CorrelationId DOUBLE, driverName STRING, routeId BIGINT, routeName STRING,eventDate STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

#### => Verify managed tables using HIVE shell
hive> DESCRIBE drivers;

hive> DESCRIBE timesheet;

hive> DESCRIBE truck_event;

#### => Verify above created tables in HDFS
$hadoop fs -ls /user/hive/warehouse

#### => Load data into respective table from local file system
hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/drivers.csv' OVERWRITE INTO TABLE drivers;

hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/timesheet.csv' OVERWRITE INTO TABLE timesheet;

hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/truck_event_text_partition.csv' OVERWRITE INTO TABLE truck_event;

#### => Verify data in HDFS
$hadoop fs -cat /user/hive/warehouse/drivers/drivers.csv

$hadoop fs -cat /user/hive/warehouse/timesheet/timesheet.csv

$hadoop fs -cat /user/hive/warehouse/truck_event/truck_event_text_partition.csv

#### => Query - Join timesheet and drivers table to get total time logged per driver along with his name.
hive> SELECT d.name, sum(t.hoursLogged) from drivers d INNER JOIN timesheet t ON (d.driverId = t.driverId) group by d.name;

#### => Query - Join drivers and truck_event table to get total number of truck event participated by driver along with his name.
hive> SELECT d.name, count(*) from drivers d INNER JOIN truck_event te ON (d.driverId = te.driverId) group by d.name;

#### => Query - Join drivers and truck_event table to get truck event details participated by driver.
hive> SELECT d.name, te.truckid, te.eventTime, te.eventType, te.routeName, te.eventDate from drivers d INNER JOIN truck_event te ON (d.driverId = te.driverId);

#### => Query - Join 3 tables using a join statement and group by driver id and count rows
hive> SELECT d.name, count(*) from drivers d INNER JOIN timesheet t ON (d.driverId = t.driverId) INNER JOIN truck_event te ON (d.driverId = te.driverId) group by d.name;

External Tables
===

#### => Load data in HDFS
$hadoop fs -mkdir /user/training/day_4/drivers

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/drivers.csv /user/training/day_4/drivers

$hadoop fs -mkdir /user/training/day_4/timesheet

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/timesheet.csv /user/training/day_4/timesheet

$hadoop fs -mkdir /user/training/day_4/truck_event

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_4/driver_data/truck_event_text_partition.csv /user/training/day_4/truck_event

#### => Create external tables (drivers_ext, timesheet_ext & truck_event_ext based on existing CSV files in HDFS)
hive> CREATE EXTERNAL TABLE drivers_ext (driverId INT, name STRING, ssn STRING, loc STRING, certified STRING, wagePlan STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/day_4/drivers';

hive> CREATE EXTERNAL TABLE timesheet_ext (driverId INT, week INT, hoursLogged INT, mileLogged INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/day_4/timesheet';

hive> CREATE EXTERNAL TABLE truck_event_ext (driverId INT, truckId INT, eventTime STRING, eventType STRING, longitude DOUBLE, latitude DOUBLE, eventKey STRING, CorrelationId DOUBLE, driverName STRING, routeId BIGINT, routeName STRING,eventDate STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/day_4/truck_event';

#### => Verify external tables using HIVE shell
hive> DESCRIBE EXTENDED drivers_ext;

hive> DESCRIBE EXTENDED timesheet_ext;

hive> DESCRIBE EXTENDED truck_event_ext;

#### => Verify above created external tables should not be part of warehouse directory
$hadoop fs -ls /user/hive/warehouse

#### => Query - Join timesheet and drivers table to get total time logged per driver along with his name.
hive> SELECT d.name, sum(t.hoursLogged) from drivers_ext d INNER JOIN timesheet_ext t ON (d.driverId = t.driverId) group by d.name;

#### => Query - Join drivers and truck_event table to get total number of truck event participated by driver along with his name.
hive> SELECT d.name, count(*) from drivers_ext d INNER JOIN truck_event_ext te ON (d.driverId = te.driverId) group by d.name;

#### => Query - Join drivers and truck_event table to get truck event details participated by driver.
hive> SELECT d.name, te.truckid, te.eventTime, te.eventType, te.routeName, te.eventDate from drivers_ext d INNER JOIN truck_event_ext te ON (d.driverId = te.driverId);

#### => Query - Join 3 tables using a join statement and group by driver id and count rows
hive> SELECT d.name, count(*) from drivers_ext d INNER JOIN timesheet_ext t ON (d.driverId = t.driverId) INNER JOIN truck_event_ext te ON (d.driverId = te.driverId) group by d.name;

Assignment : Clarification on External Tables
===

#### => Load employee data in HDFS
$hadoop fs -mkdir /user/training/day_5/emp

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_5/employees.csv /user/training/day_5/emp

$hadoop fs -cat /user/training/day_5/emp/employees.csv

#### => Create external tables
hive> CREATE EXTERNAL TABLE employees_ext (age INT, name STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/day_5/emp';

#### => Verify drivers table using HIVE shell
hive> DESCRIBE EXTENDED employees_ext;

#### => Query : Select data from external table
hive> SELECT * FROM employees_ext;


Assignment-2
========

Use the same data given in Assignment-1:

1. Create a UDF which reverses value of the column

2. Try Partitioning and Data Indexing using timesheet.csv and then Join that table with drivers and get Total hours logged by each driver.

3. Execute above hive commands using a script

