package com.cardrive.metrics;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import com.cardrive.dao.ICrudDao;

public class HibernateTest {

	public static void main(String a[]) {
		System.out.println("sdsd");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test-config.xml");
		@SuppressWarnings("unchecked")
		ICrudDao<Metric> metricDao = ctx.getBean("metricDAO", ICrudDao.class);
		System.out.println(metricDao);
		System.out.println(metricDao.read(1L).getName());
//		HibernateTemplate t = new HibernateTemplate(factory);
//		System.out.println(t.get(Metric.class, "1"));
	}
}
