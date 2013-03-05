package com.cardrive.metrics.metricvalue;

public class DoubleMetricValue extends MetricValueBase<Double>{
	
	public DoubleMetricValue(){
		
	}
	
	public DoubleMetricValue(Double value) {
		super(value);
	}
	
	@Override
	public Double getDoubleValue() {
		return value;
	}
	
	@Override
	public String objectToString() {
		return value.toString();
	}

	@Override
	public MetricValueBase<Double> stringToObject(String objectString) {
		Double value = Double.parseDouble(objectString);
		return new DoubleMetricValue(value);
	}

	@Override
	public void generateValueFromString(String value) {
		this.value = Double.parseDouble(value);		
	}
}
