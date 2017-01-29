package com.intellipaat.training.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LineIndexReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		boolean first = true;
		StringBuilder toReturn = new StringBuilder();
		
		for (Text value : values) {
			if (!first) {
				toReturn.append(", ");
			}	
			first = false;
			toReturn.append(value.toString());
		}
		context.write(key, new Text(toReturn.toString()));
	}

}
