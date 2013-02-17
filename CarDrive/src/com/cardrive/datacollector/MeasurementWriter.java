package com.cardrive.datacollector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicInteger;

import com.openxc.measurements.Measurement;
import com.openxc.measurements.serializers.JsonSerializer;

public class MeasurementWriter {
    private static String MEASUREMENT_FILE_NAME = "/sdcard/measurement-file.txt";
    private Writer writer = null;
    private int FLUSH_THRESHOLD = 100;
    private AtomicInteger numLineWritten = new AtomicInteger(0);

    public MeasurementWriter() throws IOException {
        File file = new File(MEASUREMENT_FILE_NAME);
        writer = new FileWriter(file);
    }

    public void writeMeasurement(Measurement measurement) throws IOException {
        String serializedMeasurement = JsonSerializer.serialize(
                measurement.getGenericName(), measurement.getSerializedValue(),
                measurement.getEvent(), measurement.getBirthtime());
        writer.write(serializedMeasurement);
        numLineWritten.incrementAndGet();
        flush();
    }

    public void flush() throws IOException {
        if (numLineWritten.get() > FLUSH_THRESHOLD) {
            numLineWritten.set(0);
            writer.flush();
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}
