package com.ibm.assignment.hadoop.tv.q3;

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

public class Q3Driver extends Configured implements Tool {

	@Override
	public int run(String[] allArgs) throws Exception {
		
		String[] args = new GenericOptionsParser(getConf(), allArgs).getRemainingArgs();
        
	    Job firstJob = Job.getInstance(getConf());
	    firstJob.setJarByClass(Q3Driver.class);
	                               
	    firstJob.setInputFormatClass(TextInputFormat.class);
	    firstJob.setOutputFormatClass(TextOutputFormat.class);
	                               
	    firstJob.setMapOutputKeyClass(ShowKey.class);
	    firstJob.setMapOutputValueClass(JoinGenericWritable.class);
	                               
	    MultipleInputs.addInputPath(firstJob, new Path(args[0]), TextInputFormat.class, Q3FirstViewerDataMapper.class);
	    MultipleInputs.addInputPath(firstJob, new Path(args[1]), TextInputFormat.class, Q3FirstShowDataMapper.class);
	                              
	    firstJob.setReducerClass(Q3FirstReducer.class);
	                         
	    firstJob.setSortComparatorClass(JoinSortingComparator.class);
	    firstJob.setGroupingComparatorClass(JoinGroupingComparator.class);
	                               
	    firstJob.setOutputKeyClass(NullWritable.class);
	    firstJob.setOutputValueClass(Text.class);
	    
	    Path firstJobOutputPath = new Path(allArgs[2] + File.separator + System.currentTimeMillis());
	    FileOutputFormat.setOutputPath(firstJob, firstJobOutputPath);
	    boolean status = firstJob.waitForCompletion(true);

	    // If first job is successfully then execute second job
	    if (status) {
		    Job secondJob = Job.getInstance(getConf());
		    secondJob.setJarByClass(Q3Driver.class);
		                               
		    secondJob.setInputFormatClass(TextInputFormat.class);
		    secondJob.setOutputFormatClass(TextOutputFormat.class);
		    
		    secondJob.setMapperClass(Q3SecondMapper.class);
		    secondJob.setMapOutputKeyClass(Q3SecondChannelShowKey.class);
		    secondJob.setMapOutputValueClass(IntWritable.class);
		    
		    FileInputFormat.addInputPath(secondJob, firstJobOutputPath);
		    
		    secondJob.setReducerClass(Q3SecondReducer.class);
		    secondJob.setOutputKeyClass(NullWritable.class);
		    secondJob.setOutputValueClass(Text.class);
		                               
		    FileOutputFormat.setOutputPath(secondJob, new Path(allArgs[2] + File.separator + System.currentTimeMillis()));
		    status = secondJob.waitForCompletion(true);
		    if (status) {
		        return 0;
		    } else {
		        return 1;
		    }
	        
	    } else {
	        return 1;
	    }	
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
	    int res = ToolRunner.run(conf, new Q3Driver(), args);
	    System.out.println("Response -> "+res);
	}

}
