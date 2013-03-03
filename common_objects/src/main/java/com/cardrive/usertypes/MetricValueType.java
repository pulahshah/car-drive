package com.cardrive.usertypes;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;

import com.cardrive.metrics.metricvalue.MetricValueBase;

public class MetricValueType implements EnhancedUserType {

	private final static String CLASS_NAME_FIELD = "CLASS_NAME";
	private final static String OBJECT_FIELD = "OBJECT_FIELD";
	
	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object o) throws HibernateException {
		return (Serializable) o;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		} else if (null == x || null == y) {
			return false;
		} else {
			return x.equals(y);
		}
	}

	@Override
	public int hashCode(Object o) throws HibernateException {
		return o.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
			throws HibernateException, SQLException {
		String result = resultSet.getString(names[0]);
		JsonFactory jfactory = new JsonFactory();
		
		try {
			JsonParser jParser = jfactory.createJsonParser(new StringReader(result));
			jParser.nextToken();
			jParser.nextToken();
			String fieldName = jParser.getCurrentName();
			if (fieldName != null && CLASS_NAME_FIELD.equals(fieldName)) {
				jParser.nextToken();
				String className = jParser.getText();
				jParser.nextToken();
				fieldName = jParser.getCurrentName();
				if(fieldName !=null && fieldName.equals(OBJECT_FIELD)) {
					jParser.nextToken();
					String objectString = jParser.getText();
					MetricValueBase metricValueBase = (MetricValueBase) Class.forName(className).getDeclaredConstructor().newInstance();
					metricValueBase = metricValueBase.stringToObject(objectString);
					return metricValueBase;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value,
			int index) throws HibernateException, SQLException {
		if (value == null) {
			preparedStatement.setNull(index, Types.VARCHAR);
		} else {
			JsonFactory jfactory = new JsonFactory();
			@SuppressWarnings("rawtypes")
			MetricValueBase metricValueObject = (MetricValueBase) value;
			try {
				StringWriter writer = new StringWriter();
				JsonGenerator jGenerator = jfactory.createJsonGenerator(writer);
				jGenerator.writeStartObject();
				jGenerator.writeStringField(CLASS_NAME_FIELD, metricValueObject.getClass().getName());
				jGenerator.writeStringField(OBJECT_FIELD, metricValueObject.objectToString());
				jGenerator.writeEndObject();
				jGenerator.close();
				preparedStatement.setString(index, writer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				preparedStatement.setNull(index, Types.VARCHAR);
			}
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return Object.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Object fromXMLString(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String objectToSQLString(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toXMLString(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}
}
