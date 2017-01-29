package com.intellipaat.training.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class LineIndexer extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {

		if (args.length != 2) {
			System.out.printf("Usage: %s [generic options] <input dir> <output dir>\n", getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.out);
			return -1;
		}

		@SuppressWarnings("deprecation")
		Job job = new Job(getConf(), LineIndexer.class.getName());
		job.setJarByClass(LineIndexer.class);

		job.setMapperClass(LineIndexMapper.class);
		job.setReducerClass(LineIndexReducer.class);

	    job.setMapOutputKeyClass(Text.class);
	    job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new LineIndexer(), args);
		System.exit(exitCode);
	}
}
