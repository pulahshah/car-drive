package com.cardrive;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import com.cardrive.datacollector.DataCollector;
import com.cardrive.datacollector.FordDataCollector;

public class MainActivity extends Activity {
	static private final Logger LOG = LoggerFactory.getLogger(MainActivity.class);
	private final String TAG = "Main Activity";
	static final String LOGBACK_XML = "<configuration>"
			+ "<file>/mnt/sdcard/measurement-log.gz</file>"
			+ "<appender name=\"ROLLING\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">"
			+ "<rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">"
			+ "<fileNamePattern>/mnt/sdcard/measurement-log-%d{yyyy-MM}.%i.gz</fileNamePattern>"
			+ "<timeBasedFileNamingAndTriggeringPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP\">"
			+ "<maxFileSize>1KB</maxFileSize>"
			+ "</timeBasedFileNamingAndTriggeringPolicy>" + "</rollingPolicy>"
			+ "<encoder>" + "<pattern>%msg%n</pattern>" + "</encoder>"
			+ " </appender>" + "<root level=\"DEBUG\">"
			+ "<appender-ref ref=\"ROLLING\" />" + "</root>"
			+ "</configuration>";

	private static void init() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			// load a specific logback.xml
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			lc.reset(); // override default configuration
			//configurator.doConfigure("/path/to/logback.xml");

			// hard-coded config
			configurator.doConfigure(new ByteArrayInputStream(LOGBACK_XML
					.getBytes()));

		} catch (JoranException je) {
			// StatusPrinter will handle this
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		final Button startMeasurement = (Button) findViewById(R.id.start_measurement_button);

		startMeasurement.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent dataCollectionService = new Intent(MainActivity.this,
						FordDataCollector.class);
				ComponentName n = startService(dataCollectionService);
				System.out.println(n);
				Log.d(TAG, "Started datacollection");
			}

		});
		return true;
	}

}
