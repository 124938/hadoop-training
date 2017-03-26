package com.ibm.assignment.hadoop.tv;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class ShowDataRecord implements Writable {
	
	private Text showName = new Text();
	private Text channelName = new Text();

	public ShowDataRecord() {
		
	}
	
	public ShowDataRecord(final String showName, final String channelName) {
		this.showName.set(showName);
		this.channelName.set(channelName);
	}
	
	public Text getShowName() {
		return showName;
	}

	public Text getChannelName() {
		return channelName;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.showName.readFields(in);
		this.channelName.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.showName.write(out);
		this.channelName.write(out);
	}

}
