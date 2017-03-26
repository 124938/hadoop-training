package com.ibm.assignment.hadoop.tv;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class ViewerDataRecord implements Writable {
	
	private Text showName = new Text();
	private IntWritable numberOfViewer = new IntWritable();

	public ViewerDataRecord() {
		
	}
	
	public ViewerDataRecord(final String showName, final int numberOfViewer) {
		this.showName.set(showName);
		this.numberOfViewer.set(numberOfViewer);
	}
	
	public Text getShowName() {
		return showName;
	}

	public IntWritable getNumberOfViewer() {
		return numberOfViewer;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.showName.readFields(in);
		this.numberOfViewer.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.showName.write(out);
		this.numberOfViewer.write(out);
	}

}
