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


