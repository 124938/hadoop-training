Assignment/Practice - SQOOP
========

#### => Go to MySQL
$mysql -u root -p
Enter Password: cloudera

mysql> SHOW DATABASES;

mysql> CREATE DATABASE sqoop_db;

mysql> create database sqoop_db;

mysql> create user 'sqoop'@'localhost' identified by 'sqoop';

mysql> grant all on sqoop_db.* to 'sqoop'@'localhost';

mysql> use sqoop_db;

mysql> show tables;

mysql> CREATE TABLE `cities` (`id` INTEGER UNSIGNED NOT NULL, `country` VARCHAR(50), `city` VARCHAR(150), PRIMARY KEY (`id`));

mysql> INSERT INTO `cities`(`id`, `country`, `city`) VALUES (1, "USA", "Palo Alto");

mysql> INSERT INTO `cities`(`id`, `country`, `city`) VALUES (2, "Czech Republic", "Brno");

mysql> INSERT INTO `cities`(`id`, `country`, `city`) VALUES (3, "USA", "Sunnyvale");

mysql> CREATE TABLE `staging_cities` LIKE `cities`;

mysql> CREATE TABLE `countries` (`country_id` INTEGER UNSIGNED NOT NULL, `country` VARCHAR(50), PRIMARY KEY (`country_id`));

mysql> INSERT INTO `countries`(`country_id`, `country`) VALUES (1, "USA");

mysql> INSERT INTO `countries`(`country_id`, `country`) VALUES (2, "Czech Republic");

mysql> CREATE TABLE `normcities` (`id` INTEGER UNSIGNED NOT NULL, `country_id` INTEGER UNSIGNED NOT NULL, `city` VARCHAR(150), PRIMARY KEY (`id`), FOREIGN KEY (`country_id`) REFERENCES `countries`  (`country_id`);

mysql> INSERT INTO `normcities`(`id`, `country_id`, `city`) VALUES (1, 1, "Palo Alto");

mysql> INSERT INTO `normcities`(`id`, `country_id`, `city`) VALUES (2, 2, "Brno");

mysql> INSERT INTO `normcities`(`id`, `country_id`, `city`) VALUES (3, 1, "Sunnyvale");

mysql> CREATE TABLE `visits` (`id` INTEGER UNSIGNED NOT NULL, `city` VARCHAR(50), `last_update_date` DATETIME NOT NULL, PRIMARY KEY (`id`), KEY (`last_update_date`));

mysql> INSERT INTO `visits`(`id`, `city`, `last_update_date`) VALUES(1, "Freemont", "1983-05-22 01:01:01");

mysql> INSERT INTO `visits`(`id`, `city`, `last_update_date`) VALUES(2, "Jicin", "1987-02-02 02:02:02");

mysql> show tables;


Assignment-1
========

Import cities table from MySQL to HDFS

$sqoop import
	--connect jdbc:mysql://localhost/sqoop_db 
	--username sqoop 
	--password sqoop 
	--table cities 
	--num-mappers 1 
	--target-dir /user/cloudera/sqoop_test/cities 
	--where "1=1" \
	--map-column-java id=Long

Assignment-2
========

Import all tables (exclude cities,countries) from MySQL to HDFS

$sqoop import-all-tables \
	--connect jdbc:mysql://localhost/sqoop_db \ 
	--username sqoop \
	--password sqoop \
	--warehouse-dir /user/cloudera/sqoop_test/import-all-tables \ 
	--exclude-tables cities,countries 


