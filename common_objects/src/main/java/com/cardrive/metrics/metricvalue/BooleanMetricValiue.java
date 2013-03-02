package com.cardrive.metrics.metricvalue;

public class BooleanMetricValiue extends MetricValueBase<Boolean>{
	
	public BooleanMetricValiue() {
		
	}
	
	public BooleanMetricValiue(Boolean value) {
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
			return new BooleanMetricValiue(false);
		} else if(objectString.equalsIgnoreCase(Boolean.TRUE.toString())) {
			return new BooleanMetricValiue(true);
		}
		return new BooleanMetricValiue(null);
	}

}
