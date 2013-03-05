package com.cardrive.metrics.metricvalue;

import com.cardrive.exception.ValidationException;

public class BooleanMetricValue extends MetricValueBase<Boolean>{
	
	public BooleanMetricValue() {
		
	}
	
	public BooleanMetricValue(Boolean value) {
		super(value);
	}
	
	@Override
	public Boolean getBooleanValue() {
		return value;
	}
	
	@Override
	public String objectToString() {
		return value.toString();
	}

	@Override
	public MetricValueBase<Boolean> stringToObject(String objectString) {
		if(objectString.equalsIgnoreCase(Boolean.FALSE.toString())) {
			return new BooleanMetricValue(false);
		} else if(objectString.equalsIgnoreCase(Boolean.TRUE.toString())) {
			return new BooleanMetricValue(true);
		}
		return new BooleanMetricValue(null);
	}

	@Override
	public void generateValueFromString(String value) throws ValidationException {
		if(value.equalsIgnoreCase(Boolean.FALSE.toString())) {
			this.value = false;
		} else if(value.equalsIgnoreCase(Boolean.TRUE.toString())) {
			this.value = true;
		} else {
			throw new ValidationException("Illegal Value for boolean");
		}
	}

}
