package com.cardrive.metrics;

import java.io.IOException;
import java.util.Date;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.cardrive.dao.ICrudDao;
import com.cardrive.metrics.metricvalue.LongMetricValue;
import com.cardrive.metrics.metricvalue.MetricValueBase;

public class HibernateTest {

	public static void main(String a[]) throws JsonGenerationException, JsonMappingException, IOException {
		System.out.println("sdsd");
		ObjectMapper mapper = new ObjectMapper();
		
//		MetricValueBase<Long> t = new LongMetricValue();
//		t.setValue(12L);
//		String f = mapper.writeValueAsString(t);
//		System.out.println(f);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test-config.xml");
		@SuppressWarnings("unchecked")
		ICrudDao<Metric> metricDao = ctx.getBean("metricDAO", ICrudDao.class);
		System.out.println(metricDao);
		
		Metric<Long> metric = new Metric<Long>();
		metric.setEvent("event");
		metric.setName(MetricEnum.ACCELERATORPEDALPOSITION);
		metric.setTimestamp(new Date(System.currentTimeMillis()));
		metric.setValue(new LongMetricValue(12L));
		
		//metricDao.update(metric);
		 metric = metricDao.read(5L);
		System.out.println(metricDao.read(5L).getValue().getClass());
		
		
	}
}
