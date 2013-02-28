package com.cardrive.metrics;	

@SuppressWarnings("rawtypes")
public enum MetricEnum {
	BRAKEPEDALSTATUS(Boolean.class),
	FUELLEVEL(Double.class),
	ENGINESPEED(Double.class),
	FUELCONSUMED(Double.class),
	STEERINGWHEELANGLE(Double.class),
	VEHICLESPEED(Double.class),
	ODOMETER(Double.class),
	ACCELERATORPEDALPOSITION(Double.class);

	private Class valueClass;
	
	MetricEnum(Class valueClass) {
		this.valueClass = valueClass;
	}

	public Class getValueClass() {
		return valueClass;
	}
}
