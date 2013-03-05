package com.cardrive.ftpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;

import com.cardrive.dao.ICrudDao;
import com.cardrive.exception.ValidationException;
import com.cardrive.metrics.Metric;
import com.cardrive.metrics.MetricBuilder;
import com.cardrive.metrics.metricvalue.MetricValueBase;

public class MetricFileParser {

    private final File metricFile;
    private final static String NAME_FIELD = "name";
    private final static String VALUE_FIELD = "value";
    private final static String TIMESTAMP_FIELD = "timestamp";
    private ICrudDao<Metric<? extends MetricValueBase<? extends Object>>> metricDao;

    public MetricFileParser(File metricFile, ICrudDao<Metric<? extends MetricValueBase<? extends Object>>> metricDao) {
        this.metricFile = metricFile;
        this.metricDao = metricDao;
    }

    public void parse() throws IOException  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(metricFile)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            try {
                parseLine(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseLine(String line) throws JsonParseException, IOException, ValidationException,
            IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createJsonParser(new StringReader(line));
        parser.nextToken();
        String name = parseJsonMapping(parser, NAME_FIELD);
        String value = parseJsonMapping(parser, VALUE_FIELD);
        String timestamp = parseJsonMapping(parser, TIMESTAMP_FIELD);
        metricDao.update(MetricBuilder.buildMetric(name, value, timestamp));
    }

    protected String parseJsonMapping(JsonParser parser, String expectedFieldName) throws JsonParseException,
            IOException, ValidationException {
        parser.nextToken();
        String fieldName = parser.getCurrentName();
        if (fieldName == null || !fieldName.equals(expectedFieldName)) {
            throw new ValidationException("Expected fieldName : " + expectedFieldName + "but got: " + fieldName);
        }
        parser.nextToken();
        String fieldValue = parser.getText();
        return fieldValue;
    }
}
