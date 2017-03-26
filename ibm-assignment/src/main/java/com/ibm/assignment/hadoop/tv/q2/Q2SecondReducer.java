package com.ibm.assignment.hadoop.tv.q2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Q2SecondReducer extends Reducer<Text, IntWritable, NullWritable, Text> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Q2SecondReducer.class);

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, NullWritable, Text>.Context context) throws IOException, InterruptedException {
		
		int totalNumberOfViewers = 0;
		
		for (IntWritable value : values) {
			totalNumberOfViewers += value.get();
        }
		
		LOGGER.debug("Channel Name -> {}, Total Number of viewers -> {}", key.getBytes(), totalNumberOfViewers);
		context.write(NullWritable.get(), new Text(key.toString() + " " + totalNumberOfViewers));
	}

}
