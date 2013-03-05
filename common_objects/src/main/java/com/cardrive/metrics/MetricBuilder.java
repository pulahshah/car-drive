package com.cardrive.metrics;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.cardrive.exception.ValidationException;
import com.cardrive.metrics.metricvalue.MetricValueBase;
import com.cardrive.utils.EnumUtils;

public abstract class MetricBuilder {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Metric<? extends MetricValueBase<? extends Object>> buildMetric(String name, String value, String timeStamp)
			throws ValidationException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (name == null || value == null || timeStamp == null) {
			throw new ValidationException("Null incoming data");
		}
		MetricEnum metricType = (MetricEnum) EnumUtils.safeGetEnumFromName(MetricEnum.class, name);
		MetricValueBase metricValue = metricType.getValueClass().getDeclaredConstructor().newInstance();
		metricValue.generateValueFromString(value);
		Date date = new Date(Long.parseLong(timeStamp));
		return new Metric(metricType, metricValue, date);
	}
}
