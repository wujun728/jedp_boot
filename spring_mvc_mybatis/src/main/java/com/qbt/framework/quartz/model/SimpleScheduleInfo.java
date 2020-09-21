package com.qbt.framework.quartz.model;

public class SimpleScheduleInfo {
	
	private int startDelay;					//not use it
	private int repeatInterval;          //interval Seconds
	private int repeatCount;			//count
	public int getStartDelay() {
		return startDelay;
	}
	public void setStartDelay(int startDelay) {
		this.startDelay = startDelay;
	}
	public int getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

}
