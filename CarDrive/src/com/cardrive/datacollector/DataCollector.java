package com.cardrive.datacollector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Service;
import android.os.Binder;

public abstract class DataCollector extends Service {
	static private final Logger LOG = LoggerFactory
			.getLogger(DataCollector.class);

	public class DataMapperServiceBinder extends Binder {
		DataCollector getService() {
			return DataCollector.this;
		}
	}

	public void writeData(String data) {
		LOG.debug(data);
	}
}
