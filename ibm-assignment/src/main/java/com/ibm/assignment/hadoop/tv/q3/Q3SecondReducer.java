package com.ibm.assignment.hadoop.tv.q3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Q3SecondReducer extends Reducer<Q3SecondChannelShowKey, IntWritable, NullWritable, Text> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Q3SecondReducer.class);
	
	private String maxChannelName = null;
	private String maxShowName = null;
	private int maxNumberOfViewer = 0;

	@Override
	protected void reduce(Q3SecondChannelShowKey key, Iterable<IntWritable> values, Reducer<Q3SecondChannelShowKey, IntWritable, NullWritable, Text>.Context context) throws IOException, InterruptedException {
		
		LOGGER.info("Max ==== Channel Name -> {}, Show Name -> {} & Number of viewer -> {}", maxChannelName, maxShowName, maxNumberOfViewer);
		
		for (IntWritable value : values) {
			if (maxNumberOfViewer == 0 || value.get() >= maxNumberOfViewer) {
				maxChannelName = key.getChannelName().toString();
				maxShowName = key.getShowName().toString();
				maxNumberOfViewer = value.get();
			} else {
				LOGGER.info("Current $$$$ Channel show Key is -> {} is & value is -> {}", key, value);
			}
        }
		
	}

	@Override
	protected void cleanup(Reducer<Q3SecondChannelShowKey, IntWritable, NullWritable, Text>.Context context) throws IOException, InterruptedException {
		context.write(NullWritable.get(), new Text(maxChannelName + " " + maxShowName + " " + maxNumberOfViewer));
	}
	
}
