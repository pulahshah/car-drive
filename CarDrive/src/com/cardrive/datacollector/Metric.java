package com.cardrive.datacollector;

public class Metric {
    private final String name;
    private final String value;
    private final Long timestamp;

    public Metric(String name, String value, Long timestamp) {
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
