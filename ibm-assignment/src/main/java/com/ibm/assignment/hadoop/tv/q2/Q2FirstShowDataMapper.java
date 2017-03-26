package com.ibm.assignment.hadoop.tv.q2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.ibm.assignment.hadoop.tv.JoinGenericWritable;
import com.ibm.assignment.hadoop.tv.ShowDataRecord;
import com.ibm.assignment.hadoop.tv.ShowKey;

public class Q2FirstShowDataMapper extends Mapper<LongWritable, Text, ShowKey, JoinGenericWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, ShowKey, JoinGenericWritable>.Context context) throws IOException, InterruptedException {
		String[] recordFields = value.toString().split(",");

		String showName = recordFields[0];
        String channelname = recordFields[1];
        
        if ("BAT".equals(channelname)) {
        	context.write(new ShowKey(showName, ShowKey.SHOW_RECORD), new JoinGenericWritable(new ShowDataRecord(showName, channelname)));
        }	
	}

}
