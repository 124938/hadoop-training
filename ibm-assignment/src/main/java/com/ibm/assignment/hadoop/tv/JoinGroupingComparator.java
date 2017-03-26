package com.ibm.assignment.hadoop.tv;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class JoinGroupingComparator extends WritableComparator {

	public JoinGroupingComparator() {
        super (ShowKey.class, true);
    }                             

	@SuppressWarnings("rawtypes")
    @Override
    public int compare (WritableComparable a, WritableComparable b){
    	ShowKey first = (ShowKey) a;
    	ShowKey second = (ShowKey) b;
                      
        return first.getName().compareTo(second.getName());
    }
}
