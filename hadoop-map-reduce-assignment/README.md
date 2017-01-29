Assignment-1
========

Assignment 1: Run the wordcount.jar program with a bigger dataset like the file ‘Shakespeare.zip’

Steps:

#### Make required directory in HDFS
$hadoop fs -mkdir /user/training/wordcount/input

### Copy Shakespear.txt to HDFS
$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/Shakespeare.txt /user/training/wordcount/input

### Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCount /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_full_output

### Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_full_output

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output/part-r-00000


Assignment-2
========

Assignment2: Run the mapreduce program with 6 reducers and observe the output. The command to run with multiple reducers is below :
$hadoop jar wordcount.jar WordCount -Dmapred.reduce.tasks=2 wordcountiputwordcountoput

Steps:

### Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCount -Dmapred.reduce.tasks=2 /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_full_output_mul_reducer

### Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_full_output_mul_reducer

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output_mul_reducer/part-r-00000

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output_mul_reducer/part-r-00001


Assignment-3
========

Assignment 3: Modify the word count program in such a way that from Shakespeare data files, only words that occur less than 100 times appear in your output file.

Steps:

### Modify word count program as per assignment-3 problem statement
- It is matter of putting if condition in Reducer code to write data into context

### Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCountGreatherThenHundred /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_grt_then_100_output

### Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_grt_then_100_output

$hadoop fs -cat /user/training/wordcount/output/shakespeare_grt_then_100_output/part-r-00000


Assignment-4
========

Assignment 4: The program below is a working code but all written in one file.
Separate this program in 3 files i.e. Mapper, Reducer and Driver and run with Shakespeare data. 
The code is available inverted_index, you can get that by being in Eclipse New Projectinverted_index


Steps:

### Create mapper, reducer and driver program as per assignment-4 problem statement

### Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar com.intellipaat.training.hadoop.LineIndexer /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_line_indexer

### Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_line_indexer

$hadoop fs -cat /user/training/wordcount/output/shakespeare_line_indexer/part-r-00000


Assignment/Practice - HIVE
========

### Install HIVE
$sudo yum install hive

### Pre-requisite before using hive
$sudo -u hdfs hadoop fs -mkdir /user/hive/warehouse

$hadoop fs -chmod g+w /tmp

$sudo -u hdfs hadoop fs -chmod g+w /user/hive/warehouse

$sudo -u hdfs hadoop fs -chown -R training /user/hive/warehouse

$sudo chmod 777 /var/lib/hive/metastore

### Go to HIVE shell
$hive

### Create table u_data
hive> CREATE TABLE u_data (user_id INT, movieid INT, rating INT, unixtime STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
DESCRIBE u_data;

### Load data into u_data table from file system
hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/ml-100k/u.data' OVERWRITE INTO TABLE u_data;

### Create table u_user
hive> CREATE TABLE u_user (userid INT, age INT, gender STRING, occupation STRING, zipcode STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '|' STORED AS TEXTFILE;

### Load data into u_user table from file system
hive> LOAD DATA LOCAL INPATH '/home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/ml-100k/u.user' OVERWRITE INTO TABLE u_user;

### Example of FULL OUTER JOIN using u_user and u_data table
hive> SELECT UD.*, U.occupation, U.gender, U.age FROM u_user U FULL OUTER JOIN u_data UD on (U.userid = UD.user_id) LIMIT 10;

### Query to count number of MALE/FEMALE user
hive> SELECT U.gender, COUNT(DISTINCT U.userid) FROM u_user U GROUP By U.gender;

### Create table u_gender_sum
hive> CREATE TABLE u_gender_sum (gender string, count int) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' STORED AS TEXTFILE;
 
### Populate u_gender table from existing table
hive> FROM u_user INSERT OVERWRITE TABLE u_gender_sum SELECT u_user.gender, count(DISTINCT u_user.userid) GROUP BY u_user.gender;


Assignment/Practice - PIG
========

### Install PIG
$sudo yum install pig

### Copy weblogs directory into HDFS
$hadoop fs -mkdir /user/training/weblogs/input

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/weblogs_parse.txt /user/training/weblogs/input

$cd /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/pig_programs
