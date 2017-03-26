package com.ibm.assignment.hadoop.tv.q1;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
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

public class Q1Driver extends Configured implements Tool {

	@Override
	public int run(String[] allArgs) throws Exception {
		
		String[] args = new GenericOptionsParser(getConf(), allArgs).getRemainingArgs();
        
	    Job job = Job.getInstance(getConf());
	    job.setJarByClass(Q1Driver.class);
	                               
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	                               
	    job.setMapOutputKeyClass(ShowKey.class);
	    job.setMapOutputValueClass(JoinGenericWritable.class);
	    //job.setCombinerClass(Q1Reducer.class);
	                               
	    MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, Q1ViewerDataMapper.class);
	    MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, Q1ShowDataMapper.class);
	                              
	    job.setReducerClass(Q1Reducer.class);
	                         
	    job.setSortComparatorClass(JoinSortingComparator.class);
	    job.setGroupingComparatorClass(JoinGroupingComparator.class);
	                               
	    job.setOutputKeyClass(NullWritable.class);
	    job.setOutputValueClass(Text.class);
	                               
	    FileOutputFormat.setOutputPath(job, new Path(allArgs[2] + File.separator + System.currentTimeMillis()));
	    boolean status = job.waitForCompletion(true);
	    if (status) {
	        return 0;
	    } else {
	        return 1;
	    }	
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
	    int res = ToolRunner.run(conf, new Q1Driver(), args);
	    System.out.println("Response -> "+res);
	}

}
