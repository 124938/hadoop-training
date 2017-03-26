package com.ibm.assignment.hadoop.tv.q3;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Q3SecondChannelShowKey implements WritableComparable<Q3SecondChannelShowKey> {
	
	private Text channelName = new Text();
	private Text showName = new Text();

	public Q3SecondChannelShowKey() {
		
	}
	
	public Q3SecondChannelShowKey(final String channelName, final String showName) {
		this.channelName.set(channelName);
		this.showName.set(showName);
	}
	

	public Text getChannelName() {
		return channelName;
	}

	public Text getShowName() {
		return showName;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.channelName.readFields(in);
		this.showName.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.channelName.write(out);
		this.showName.write(out);
	}

	@Override
	public int compareTo(Q3SecondChannelShowKey other) {
		if (this.channelName.equals(other.getChannelName())) {
	        return this.showName.compareTo(other.getShowName());
	    } else {
	        return this.channelName.compareTo(other.getChannelName());
	    }
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channelName == null) ? 0 : channelName.hashCode());
		result = prime * result
				+ ((showName == null) ? 0 : showName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Q3SecondChannelShowKey other = (Q3SecondChannelShowKey) obj;
		if (channelName == null) {
			if (other.channelName != null)
				return false;
		} else if (!channelName.equals(other.channelName))
			return false;
		if (showName == null) {
			if (other.showName != null)
				return false;
		} else if (!showName.equals(other.showName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Q3SecondChannelShowKey [channelName=");
		builder.append(channelName);
		builder.append(", showName=");
		builder.append(showName);
		builder.append("]");
		return builder.toString();
	}

	 
}
