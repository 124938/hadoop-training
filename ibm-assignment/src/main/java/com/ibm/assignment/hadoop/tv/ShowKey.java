package com.ibm.assignment.hadoop.tv;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class ShowKey implements WritableComparable<ShowKey> {
	
	public static final IntWritable SHOW_RECORD = new IntWritable(0);
	public static final IntWritable VIEWER_RECORD = new IntWritable(1);
	
	private Text name = new Text();
	private IntWritable recordType = new IntWritable();

	public ShowKey() {
		
	}
	
	public ShowKey(final String name, final IntWritable recordType) {
		this.name.set(name);
		this.recordType = recordType;
	}
	
	public Text getName() {
		return name;
	}

	public IntWritable getRecordType() {
		return recordType;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.name.readFields(in);
		this.recordType.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.name.write(out);
		this.recordType.write(out);
	}

	@Override
	public int compareTo(ShowKey other) {
		if (this.name.equals(other.getName())) {
	        return this.recordType.compareTo(other.recordType);
	    } else {
	        return this.getName().compareTo(other.getName());
	    }
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		ShowKey other = (ShowKey) obj;
		return this.name.equals(other.getName()) && this.recordType.equals(other.recordType );
	}
	 
}
