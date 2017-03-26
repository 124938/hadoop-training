package com.ibm.assignment.hadoop.tv;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ViewerDataMapper extends Mapper<LongWritable, Text, ShowKey, JoinGenericWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, ShowKey, JoinGenericWritable>.Context context) throws IOException, InterruptedException {
		String[] recordFields = value.toString().split(",");

		String showName = recordFields[0];
        int numberOfViewer = Integer.parseInt(recordFields[1]);
                                               
        context.write(new ShowKey(showName, ShowKey.VIEWER_RECORD), new JoinGenericWritable(new ViewerDataRecord(showName, numberOfViewer)));
	}

}
