package com.ibm.assignment.hadoop.tv;

import org.apache.hadoop.io.GenericWritable;
import org.apache.hadoop.io.Writable;

@SuppressWarnings("unchecked")
public class JoinGenericWritable extends GenericWritable {

	private static Class<? extends Writable>[] CLASSES = null;

	static {
		CLASSES = (Class<? extends Writable>[]) new Class[] { ViewerDataRecord.class, ShowDataRecord.class };
	}

	public JoinGenericWritable() {
		
	}

	public JoinGenericWritable(Writable instance) {
		set(instance);
	}

	@Override
	protected Class<? extends Writable>[] getTypes() {
		return CLASSES;
	}

}
