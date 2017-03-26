package com.ibm.assignment.hadoop.tv.q3;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.assignment.hadoop.tv.JoinGenericWritable;
import com.ibm.assignment.hadoop.tv.ShowDataRecord;
import com.ibm.assignment.hadoop.tv.ShowKey;
import com.ibm.assignment.hadoop.tv.ViewerDataRecord;

public class Q3FirstReducer extends Reducer<ShowKey, JoinGenericWritable, NullWritable, Text> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Q3FirstReducer.class);

	@Override
	protected void reduce(ShowKey key, Iterable<JoinGenericWritable> values, Reducer<ShowKey, JoinGenericWritable, NullWritable, Text>.Context context) throws IOException, InterruptedException {
		
		String channelName = null;
		String showName = null;
		int showWiseNumberOfViewers = 0;
		
		for (JoinGenericWritable v : values) {
            Writable record = v.get();
            
            if (key.getRecordType().equals(ShowKey.SHOW_RECORD)){
                ShowDataRecord showNameDataRecord = (ShowDataRecord) record;
                channelName = showNameDataRecord.getChannelName().toString();
                showName = showNameDataRecord.getShowName().toString();
            } else {
                ViewerDataRecord viewerDataRecord = (ViewerDataRecord) record;
                showWiseNumberOfViewers += Integer.parseInt(viewerDataRecord.getNumberOfViewer().toString());
            }
        }
		
		if (showName != null && channelName != null) {
			context.write(NullWritable.get(), new Text(channelName + " " + showName + " " + showWiseNumberOfViewers));
		} else {
			LOGGER.warn("Mapper does not contain any record with show name -> {}", key.getName());
		}
	}

}
