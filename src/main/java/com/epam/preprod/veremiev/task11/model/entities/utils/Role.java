package com.epam.preprod.veremiev.task11.model.entities.utils;

/**
 * User roles
 * 
 * @author Illia_Veremiev
 *
 */
public enum Role {
	ADMIN("admin"), CUSTOMER("customer");

	private String value;

	private Role(String value) {
		this.value = value;
	}

	/**
	 * Returns {@link Role} instance by text representation
	 * 
	 * @param value - string value
	 * @return {@link Role} instance by text representation
	 */
	public static Role forValue(String value) {
		if (value == null) {
			return null;
		}
		Role[] roles = Role.values();
		value = value.toLowerCase();
		for (Role role : roles) {
			if (role.name().toLowerCase().equals(value)) {
				return role;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
