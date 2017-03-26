package com.ibm.assignment.hadoop.tv.q3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q3SecondMapper extends Mapper<LongWritable, Text, Q3SecondChannelShowKey, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Q3SecondChannelShowKey, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] recordFields = value.toString().split(" ");

		String channelName = recordFields[0];
		String showName = recordFields[1];
        int numberOfViewer = Integer.parseInt(recordFields[2]);
                                               
        context.write(new Q3SecondChannelShowKey(channelName, showName), new IntWritable(numberOfViewer));
	}

}
