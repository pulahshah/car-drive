package com.cardrive.metrics;	

import com.cardrive.metrics.metricvalue.BooleanMetricValiue;
import com.cardrive.metrics.metricvalue.DoubleMetricValue;
import com.cardrive.metrics.metricvalue.LongMetricValue;
import com.cardrive.metrics.metricvalue.MetricValueBase;

@SuppressWarnings("rawtypes")
public enum MetricEnum {
	BRAKEPEDALSTATUS(BooleanMetricValiue.class),
	FUELLEVEL(LongMetricValue.class),
	ENGINESPEED(DoubleMetricValue.class),
	FUELCONSUMED(DoubleMetricValue.class),
	STEERINGWHEELANGLE(DoubleMetricValue.class),
	VEHICLESPEED(DoubleMetricValue.class),
	ODOMETER(DoubleMetricValue.class),
	ACCELERATORPEDALPOSITION(DoubleMetricValue.class);

	private Class<? extends MetricValueBase> valueClass;
	
	MetricEnum(Class<? extends MetricValueBase> valueClass) {
		this.valueClass = valueClass;
	}

	public Class<? extends MetricValueBase> getValueClass() {
		return valueClass;
	}
}
