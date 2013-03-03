package com.cardrive.metrics;

import java.io.IOException;
import java.util.Date;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.cardrive.dao.ICrudDao;
import com.cardrive.metrics.metricvalue.LongMetricValue;

public class HibernateTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String a[]) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("sdsd");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate-config.xml");
		ICrudDao<Metric> metricDao = ctx.getBean("metricDAO", ICrudDao.class);
		System.out.println(metricDao);
		
		Metric<Long> metric = new Metric<Long>();
		metric.setEvent("event");
		metric.setName(MetricEnum.ACCELERATORPEDALPOSITION);
		metric.setTimestamp(new Date(System.currentTimeMillis()));
		metric.setValue(new LongMetricValue(12L));
		
		metricDao.update(metric);
		metric = metricDao.read(metric.getId());
		System.out.println(metric.getValue().getClass());
	}
}
