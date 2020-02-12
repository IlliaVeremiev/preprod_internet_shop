package com.epam.preprod.veremiev.task11.model.entities.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Define device keys switches type
 * 
 * @author Illia_Veremiev
 *
 */
public enum KeySwitches {
	BROWN, RED, BLUE;

	/**
	 * Returns {@link KeySwitches} instance by string representation
	 * 
	 * @param value - string value
	 * @return {@link KeySwitches} instance by string representation
	 */
	@JsonCreator
	public static KeySwitches forValue(String value) {
		if (value == null) {
			return null;
		}
		KeySwitches[] sizes = KeySwitches.values();
		value = value.toLowerCase();
		for (KeySwitches mouseSize : sizes) {
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
