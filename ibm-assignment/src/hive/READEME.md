HIVE Solution
========

=> Create external tables (TV_SHOW_CHANNEL, TV_SHOW_VIEWER based on existing CSV files in HDFS)

hive> CREATE EXTERNAL TABLE TV_SHOW_CHANNEL (SHOW_NAME STRING, CHANNEL_NAME STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/ibm/tv/channel';

hive> CREATE EXTERNAL TABLE TV_SHOW_VIEWER (SHOW_NAME STRING, VIEWER_NUM INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '/user/training/ibm/tv/viewer';

=> What is the total number of viewers for shows on ABC?

hive> SELECT * FROM TV_SHOW_CHANNEL c WHERE c.CHANNEL_NAME = 'ABC';

hive> SELECT c.SHOW_NAME, SUM(v.VIEWER_NUM) FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) WHERE c.CHANNEL_NAME = 'ABC' GROUP BY c.SHOW_NAME;

Answer: 
Almost_Games	49237
Almost_News	46592
Almost_Show	50202
Baked_Games	51604
Baked_News	47211
Cold_News	47924
Cold_Sports	52005
Dumb_Show	53824
Dumb_Talking	103894
Hot_Games	50228
Hot_Show	54378
Hourly_Cooking	54208
Hourly_Show	48283
Hourly_Talking	108163
Loud_Games	49482
Loud_Show	50820
PostModern_Games	50644
PostModern_News	50021
Surreal_News	50420
Surreal_Sports	46834

=> What is the number of viewers for the BAT channel?

hive> SELECT c.CHANNEL_NAME, SUM(v.VIEWER_NUM) FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) WHERE c.CHANNEL_NAME = 'BAT' GROUP BY c.CHANNEL_NAME;

Answer:
BAT	5099141

=> What is the most viewed show on ABC channel?

hive> SELECT c.SHOW_NAME, SUM(v.VIEWER_NUM) as VIEWER_COUNT FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) WHERE c.CHANNEL_NAME = 'ABC' GROUP BY c.SHOW_NAME ORDER BY VIEWER_COUNT DESC LIMIT 1;

Answer:
Hourly_Talking	108163

=> What are the aired shows on ZOO, NOX, ABC channels ?



