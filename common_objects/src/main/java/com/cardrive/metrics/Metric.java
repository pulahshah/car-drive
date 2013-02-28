package com.cardrive.metrics;

import java.util.Date;

public class Metric extends BaseObject {
	
	private Date timeStamp;
	private String name;
	private Long value;
	private String event;
	
	public Date getTimestamp() {
		return timeStamp;
	}
	
	public void setTimestamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
