Assignment-1
========

Assignment 1: Run the wordcount.jar program with a bigger dataset like the file ‘Shakespeare.zip’

TEST

Steps:

#### => Make required directory in HDFS
$hadoop fs -mkdir /user/training/wordcount/input

#### => Copy Shakespear.txt to HDFS
$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/Shakespeare.txt /user/training/wordcount/input

#### => Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCount /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_full_output

#### => Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_full_output

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output/part-r-00000


Assignment-2
========

Assignment2: Run the mapreduce program with 6 reducers and observe the output. The command to run with multiple reducers is below :
$hadoop jar wordcount.jar WordCount -Dmapred.reduce.tasks=2 wordcountiputwordcountoput

Steps:

#### => Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCount -Dmapred.reduce.tasks=2 /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_full_output_mul_reducer

#### => Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_full_output_mul_reducer

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output_mul_reducer/part-r-00000

$hadoop fs -cat /user/training/wordcount/output/shakespeare_full_output_mul_reducer/part-r-00001


Assignment-3
========

Assignment 3: Modify the word count program in such a way that from Shakespeare data files, only words that occur less than 100 times appear in your output file.

Steps:

#### => Modify word count program as per assignment-3 problem statement
- It is matter of putting if condition in Reducer code to write data into context

#### => Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar WordCountGreatherThenHundred /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_grt_then_100_output

#### => Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_grt_then_100_output

$hadoop fs -cat /user/training/wordcount/output/shakespeare_grt_then_100_output/part-r-00000


Assignment-4
========

Assignment 4: The program below is a working code but all written in one file.
Separate this program in 3 files i.e. Mapper, Reducer and Driver and run with Shakespeare data. 
The code is available inverted_index, you can get that by being in Eclipse New Projectinverted_index


Steps:

#### => Create mapper, reducer and driver program as per assignment-4 problem statement

#### => Execute Map Reduce program using Shakespeare.txt
$hadoop jar wordcount.jar com.intellipaat.training.hadoop.LineIndexer /user/training/wordcount/input/Shakespeare.txt /user/training/wordcount/output/shakespeare_line_indexer

#### => Verify the output of above execution
$hadoop fs -ls -R /user/training/wordcount/output/shakespeare_line_indexer

$hadoop fs -cat /user/training/wordcount/output/shakespeare_line_indexer/part-r-00000


Assignment/Practice - PIG
========

#### => Install PIG
$sudo yum install pig

### Copy weblogs directory into HDFS
$hadoop fs -mkdir /user/training/weblogs/input

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/weblogs_parse.txt /user/training/weblogs/input

$cd /home/training/Downloads/intellipaat_hadoop_training/day_3/intellipaat/pig_programs
