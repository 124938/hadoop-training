--3. What is the most viewed show on ABC channel?

tv_show_channel = LOAD '/home/training/Downloads/intellipaat_hadoop_training/ibm/tv/channel/*.txt' 
			USING PigStorage(',') 
			as (show_name:chararray, channel_name:Chararray);

tv_show_viewer = LOAD '/home/training/Downloads/intellipaat_hadoop_training/ibm/tv/viewer/*.txt' 
			USING PigStorage(',') 
			as (show_name:chararray, viewer_num:int);

tv_show_channel_filter = FILTER tv_show_channel by channel_name == 'ABC';

tv_show_channel_viewer_join = JOIN tv_show_channel_filter BY show_name, tv_show_viewer BY show_name;

tv_show_channel_viewer_group = GROUP tv_show_channel_viewer_join BY tv_show_channel_filter::show_name;

tv_show_channel_viewer_group_order = ORDER tv_show_channel_viewer_group BY group;

result_int_1 = FOREACH tv_show_channel_viewer_group_order 
		GENERATE group, SUM(tv_show_channel_viewer_join.tv_show_viewer::viewer_num);

result_int_2 = ORDER result_int_1 by $1 DESC;

result = LIMIT result_int_2 BY 1;

DUMP result;

/*

=======
Result:
=======

(Hourly_Talking,108163)

*/


