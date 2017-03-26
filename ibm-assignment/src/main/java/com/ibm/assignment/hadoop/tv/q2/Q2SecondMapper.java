package com.ibm.assignment.hadoop.tv.q2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q2SecondMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] recordFields = value.toString().split(",");

		//String showName = recordFields[0];
		String channelName = recordFields[1];
        int numberOfViewer = Integer.parseInt(recordFields[2]);
                                               
        context.write(new Text(channelName), new IntWritable(numberOfViewer));
	}

}
