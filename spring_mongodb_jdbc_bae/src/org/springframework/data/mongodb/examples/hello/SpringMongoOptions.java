package org.springframework.data.mongodb.examples.hello;

import com.mongodb.MongoOptions;

public class SpringMongoOptions extends MongoOptions {
	private boolean cursorFinalizerEnabled = false;
	public SpringMongoOptions() {
		super();
	}
	public void setCursorFinalizerEnabled() {
		super.setCursorFinalizerEnabled(cursorFinalizerEnabled);
	}
}
