package com.cardrive.datacollector;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.openxc.measurements.Measurement;

public class FordMeasurementSerializer implements MeasurementSerializer {
    private static DecimalFormat sTimestampFormatter =
            new DecimalFormat("##########.000000");

    private final Measurement measurement;

    public FordMeasurementSerializer(Measurement measurement) {
        this.measurement = measurement;
    }

    public String serialize() {

        StringWriter buffer = new StringWriter(64);
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonGenerator gen = jsonFactory.createJsonGenerator(buffer);

            gen.writeStartObject();
            gen.writeStringField(NAME_FIELD, measurement.getGenericName());
            Object value = measurement.getSerializedValue();
            if(value != null) {
                gen.writeObjectField(VALUE_FIELD, value);
            }
            Object event = measurement.getSerializedEvent();
            if(event != null) {
                gen.writeObjectField(EVENT_FIELD, event);
            }

            Double timestamp = measurement.getBirthtime();
            if(timestamp != null) {
                gen.writeFieldName(TIMESTAMP_FIELD);
                gen.writeRawValue(sTimestampFormatter.format(timestamp));
            }

            gen.writeEndObject();
            gen.close();
        } catch(IOException e) {
            Log.w("Serailizer", "Unable to encode all data to JSON -- " +
                    "message may be incomplete", e);
        }
        return buffer.toString();
    }

}
