package com.cardrive.metrics.metricvalue;

public class LongMetricValue extends MetricValueBase<Long> {
	
	public LongMetricValue() {
	}
	
	public LongMetricValue(Long value) {
		super(value);
	}
	
	@Override
	public Long getLongValue() {
		return value;
	}

	@Override
	public String objectToString() {
		return value.toString();
	}

	@Override
	public MetricValueBase<Long> stringToObject(String objectString) {
		Long value = Long.parseLong(objectString);
		return new LongMetricValue(value);
	}

	@Override
	public void generateValueFromString(String value) {
		this.value = Long.parseLong(value);
	}
}
