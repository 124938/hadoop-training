-- Create external tables (TV_SHOW_CHANNEL, TV_SHOW_VIEWER based on existing CSV files in HDFS)

CREATE EXTERNAL TABLE TV_SHOW_CHANNEL (SHOW_NAME STRING, CHANNEL_NAME STRING) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE LOCATION '/user/training/ibm/tv/channel';

CREATE EXTERNAL TABLE TV_SHOW_VIEWER (SHOW_NAME STRING, VIEWER_NUM INT) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE LOCATION '/user/training/ibm/tv/viewer';

