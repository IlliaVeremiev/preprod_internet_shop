package com.epam.preprod.veremiev.task11.model.entities.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Define mouse size
 * 
 * @author Illia_Veremiev
 *
 */
public enum MouseSize {
	SMALL, MEDIUM, LARGE;

	/**
	 * Returns {@link MouseSize} instance by string representation
	 * 
	 * @param value - string value
	 * @return {@link MouseSize} instance by string representation
	 */
	@JsonCreator
	public static MouseSize forValue(String value) {
		if (value == null) {
			return null;
		}
		MouseSize[] sizes = MouseSize.values();
		value = value.toLowerCase();
		for (MouseSize mouseSize : sizes) {
			if (mouseSize.name().toLowerCase().equals(value)) {
				return mouseSize;
			}
		}
		return null;
	}

	/**
	 * Returns instance name
	 * 
	 * @return instance name
	 */
	@JsonValue
	public String toValue() {
		return this.name();
	}
}
