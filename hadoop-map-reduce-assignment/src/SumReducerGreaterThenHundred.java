import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducerGreaterThenHundred extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	    int wordCount = 0;
	    for (IntWritable value : values) {
	    	wordCount += value.get();
	    }
	    
	    // Write in context if word count size is greater then 100
	    if (wordCount > 100) {
	    	context.write(key, new IntWritable(wordCount));
	    }
	}

}
