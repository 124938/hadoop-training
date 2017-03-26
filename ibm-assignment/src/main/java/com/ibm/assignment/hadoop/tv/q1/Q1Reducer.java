package com.ibm.assignment.hadoop.tv.q1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.assignment.hadoop.tv.JoinGenericWritable;
import com.ibm.assignment.hadoop.tv.ShowDataRecord;
import com.ibm.assignment.hadoop.tv.ShowKey;
import com.ibm.assignment.hadoop.tv.ViewerDataRecord;

public class Q1Reducer extends Reducer<ShowKey, JoinGenericWritable, Text, IntWritable> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Q1Reducer.class);

	@Override
	protected void reduce(ShowKey key, Iterable<JoinGenericWritable> values, Reducer<ShowKey, JoinGenericWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		String showName = null;
		int showWiseNumberOfViewers = 0;
		
		for (JoinGenericWritable v : values) {
            Writable record = v.get();
            
            if (key.getRecordType().equals(ShowKey.SHOW_RECORD)){
                ShowDataRecord showNameDataRecord = (ShowDataRecord) record;
                showName = showNameDataRecord.getShowName().toString();
            } else {
                ViewerDataRecord viewerDataRecord = (ViewerDataRecord) record;
                showWiseNumberOfViewers += Integer.parseInt(viewerDataRecord.getNumberOfViewer().toString());
            }
        }
		
		if (showName != null) {
			context.write(new Text(showName), new IntWritable(showWiseNumberOfViewers));
		} else {
			LOGGER.warn("Mapper does not contain any record with show name -> {}", key.getName());
		}
	}

}
