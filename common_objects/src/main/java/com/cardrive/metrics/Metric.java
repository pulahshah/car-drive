package com.cardrive.metrics;

import java.util.Date;

import com.cardrive.metrics.metricvalue.MetricValueBase;

public class Metric<T> extends BaseObject {

	private Date timeStamp;
	private MetricEnum name;
	private MetricValueBase<T> value;

	public Metric() {
		
	}
	
	public Metric(MetricEnum name, MetricValueBase<T> value, Date timeStamp) {
		this.name = name;
		this.value = value;
		this.timeStamp = timeStamp;
	}

	public Date getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public MetricValueBase<T> getValue() {
		return value;
	}

	public void setValue(MetricValueBase<T> value) {
		this.value = value;
	}

	public MetricEnum getName() {
		return name;
	}

	public void setName(MetricEnum name) {
		this.name = name;
	}
}
