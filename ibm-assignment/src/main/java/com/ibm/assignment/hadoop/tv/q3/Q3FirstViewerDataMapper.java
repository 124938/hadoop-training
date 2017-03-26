package com.ibm.assignment.hadoop.tv.q3;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.ibm.assignment.hadoop.tv.JoinGenericWritable;
import com.ibm.assignment.hadoop.tv.ShowKey;
import com.ibm.assignment.hadoop.tv.ViewerDataMapper;

public class Q3FirstViewerDataMapper extends ViewerDataMapper {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, ShowKey, JoinGenericWritable>.Context context) throws IOException, InterruptedException {
		super.map(key, value, context);	
	}

}
