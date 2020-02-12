package com.epam.preprod.veremiev.task11.core.validators.impl;

import org.apache.commons.lang3.StringUtils;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Validates that category fit some rules
 * 
 * @author Illia_Veremiev
 *
 */
public class CategoryValidator extends ConvertValidator<String> {

	/**
	 * Constructor with parameter
	 * 
	 * @param value - string value
	 */
	public CategoryValidator(String value) {
		super(value);
	}

	@Override
	public boolean isCastable() {
		if (value == null || "".equals(value)) {
			return false;
		}
		return StringUtils.isNumeric(value);
	}

	@Override
	public String getConverted() {
		return value;
	}
}