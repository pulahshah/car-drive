package com.cardrive.metrics.metricvalue;

import com.cardrive.exception.ValidationException;

public abstract class MetricValueBase<T extends Object> {
	protected T value;
	
	public MetricValueBase() {
	}

	public MetricValueBase(T value) {
		this.value = value;
	}

	public abstract void generateValueFromString(String value) throws ValidationException;
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public Long getLongValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create long value.");
	}
	
	public int getIntegerValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create integer value.");
	}
	
	public Double getDoubleValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create double value.");
	}
	
	public Boolean getBooleanValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create boolean value.");
	}
	
	public Float getFloatValue() throws IllegalAccessException {
		throw new IllegalAccessException("Can't create float value.");
	}
	
	public abstract String objectToString();
	
	public abstract MetricValueBase<T> stringToObject(String objectString);
}
