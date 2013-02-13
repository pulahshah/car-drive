package com.carmetrics.mappers;

import android.app.Service;
import android.os.Binder;

public abstract class DataMapper extends Service {
	public class DataMapperServiceBinder extends Binder {
		DataMapper getService() {
			return DataMapper.this;
		}
	}

	public abstract Metric init();

	public void sendData() {
		// TODO: Fill it when DataWriterService ready
	}
}
