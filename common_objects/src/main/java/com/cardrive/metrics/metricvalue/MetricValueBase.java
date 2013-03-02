package com.cardrive.metrics.metricvalue;

public abstract class MetricValueBase<T> {
	protected T value;
	
	public MetricValueBase() {
	}

	public MetricValueBase(T value) {
		this.value = value;
	}

	
	public void setValue(T value) {
		this.value = value;
	}
	
	public Long getLongValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public int getIntegerValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public Double getDoubleValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public Boolean getBooleanValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public Float getFloatValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public abstract String objectToString();
	
	public abstract MetricValueBase<T> stringToObject(String objectString);
}
