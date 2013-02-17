package com.cardrive.datacollector;

public interface MeasurementSerializer {
    public static final String NAME_FIELD = "name";
    public static final String VALUE_FIELD = "value";
    public static final String EVENT_FIELD = "event";
    public static final String TIMESTAMP_FIELD = "timestamp";

    public String serialize();

}
