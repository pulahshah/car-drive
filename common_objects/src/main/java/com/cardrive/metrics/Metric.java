package com.cardrive.metrics;

import java.util.Date;

import com.cardrive.metrics.metricvalue.MetricValueBase;

public class Metric<T> extends BaseObject {

	private Date timeStamp;
	private MetricEnum name;
	private MetricValueBase<T> value;
	private String event;
	private Class<? extends MetricValueBase<T>> metricValueClass;

	public Class<? extends MetricValueBase<T>> getMetricValueClass() {
		return metricValueClass;
	}

	public void setMetricValueClass(
			Class<? extends MetricValueBase<T>> metricValueClass) {
		this.metricValueClass = metricValueClass;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}		
}
