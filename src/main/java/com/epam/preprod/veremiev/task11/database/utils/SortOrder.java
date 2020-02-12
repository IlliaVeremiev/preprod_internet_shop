package com.epam.preprod.veremiev.task11.database.utils;

import com.epam.preprod.veremiev.task11.constants.DBFields;

public enum SortOrder {
	NAME(DBFields.INDEV_TITLE), PRICE(DBFields.INDEV_PRICE);

	private static final SortOrder DEFAULT = NAME;

	private String value;

	private SortOrder(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static SortOrder getByName(String name) {
		if (name == null) {
			return DEFAULT;
		}
		SortOrder[] orderings = SortOrder.values();
		name = name.toLowerCase();
		for (SortOrder ordering : orderings) {
			if (ordering.value.toLowerCase().equals(name)) {
				return ordering;
			}
		}
		return DEFAULT;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
