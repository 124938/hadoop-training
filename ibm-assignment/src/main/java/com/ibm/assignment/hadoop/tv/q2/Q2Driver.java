package com.ibm.assignment.hadoop.tv.q2;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.ibm.assignment.hadoop.tv.JoinGenericWritable;
import com.ibm.assignment.hadoop.tv.JoinGroupingComparator;
import com.ibm.assignment.hadoop.tv.JoinSortingComparator;
import com.ibm.assignment.hadoop.tv.ShowKey;

public class Q2Driver extends Configured implements Tool {

	@Override
	public int run(String[] allArgs) throws Exception {
		
		String[] args = new GenericOptionsParser(getConf(), allArgs).getRemainingArgs();
        
	    Job firstJob = Job.getInstance(getConf());
	    firstJob.setJarByClass(Q2Driver.class);
	                               
	    firstJob.setInputFormatClass(TextInputFormat.class);
	    firstJob.setOutputFormatClass(TextOutputFormat.class);
	                               
	    MultipleInputs.addInputPath(firstJob, new Path(args[0]), TextInputFormat.class, Q2FirstViewerDataMapper.class);
	    MultipleInputs.addInputPath(firstJob, new Path(args[1]), TextInputFormat.class, Q2FirstShowDataMapper.class);
	    
	    firstJob.setMapOutputKeyClass(ShowKey.class);
	    firstJob.setMapOutputValueClass(JoinGenericWritable.class);
	                              
	    firstJob.setSortComparatorClass(JoinSortingComparator.class);
	    firstJob.setGroupingComparatorClass(JoinGroupingComparator.class);

	    firstJob.setReducerClass(Q2FirstReducer.class);
	    firstJob.setOutputKeyClass(NullWritable.class);
	    firstJob.setOutputValueClass(Text.class);
	    
	    Path firstJobOutputPath = new Path(allArgs[2] + File.separator + System.currentTimeMillis());
	    FileOutputFormat.setOutputPath(firstJob, firstJobOutputPath);
	    boolean status = firstJob.waitForCompletion(true);
	    /*if (status) {
	        return 0;
	    } else {
	        return 1;
	    }*/
	    
	    Job secondJob = Job.getInstance(getConf());
	    secondJob.setJarByClass(Q2Driver.class);
	                               
	    secondJob.setInputFormatClass(TextInputFormat.class);
	    secondJob.setOutputFormatClass(TextOutputFormat.class);
	    
	    secondJob.setMapperClass(Q2SecondMapper.class);
	    secondJob.setMapOutputKeyClass(Text.class);
	    secondJob.setMapOutputValueClass(IntWritable.class);
	    
	    FileInputFormat.addInputPath(secondJob, firstJobOutputPath);
	    
	    secondJob.setReducerClass(Q2SecondReducer.class);
	    secondJob.setOutputKeyClass(Text.class);
	    secondJob.setOutputValueClass(IntWritable.class);
	                               
	    FileOutputFormat.setOutputPath(secondJob, new Path(allArgs[2] + File.separator + System.currentTimeMillis()));
	    status = secondJob.waitForCompletion(true);
	    if (status) {
	        return 0;
	    } else {
	        return 1;
	    }
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
	    int res = ToolRunner.run(conf, new Q2Driver(), args);
	    System.out.println("Response -> "+res);
	}

}
