--2. What is the number of viewers for the BAT channel?

tv_show_channel = LOAD '/home/training/Downloads/intellipaat_hadoop_training/ibm/tv/channel/*.txt' 
			USING PigStorage(',') 
			as (show_name:chararray, channel_name:Chararray);

tv_show_viewer = LOAD '/home/training/Downloads/intellipaat_hadoop_training/ibm/tv/viewer/*.txt' 
			USING PigStorage(',') 
			as (show_name:chararray, viewer_num:int);

tv_show_channel_distinct = DISTINCT tv_show_channel;

tv_show_channel_filter = FILTER tv_show_channel_distinct by channel_name == 'BAT';

tv_show_channel_viewer_join = JOIN tv_show_channel_filter BY show_name, tv_show_viewer BY show_name;

tv_show_channel_viewer_group = GROUP tv_show_channel_viewer_join BY tv_show_channel_filter::channel_name;

tv_show_channel_viewer_group_order = ORDER tv_show_channel_viewer_group BY group;

result = FOREACH tv_show_channel_viewer_group_order 
		GENERATE group, SUM(tv_show_channel_viewer_join.tv_show_viewer::viewer_num);

DUMP result;

/*
========
Result:
========

(BAT,3031762)

*/


