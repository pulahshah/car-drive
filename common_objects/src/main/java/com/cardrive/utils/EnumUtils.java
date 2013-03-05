package com.cardrive.utils;

public abstract class EnumUtils {
	public static <E extends Enum<E>>  Enum<E> safeGetEnumFromName(Class<E> e, String enumName) {
		if(enumName == null) {
			return null;
		}
		return Enum.valueOf(e, enumName);
	}
}
