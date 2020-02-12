package com.epam.preprod.veremiev.task11.core.validators.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Validates name by regular expression
 * 
 * @author Illia_Veremiev
 *
 */
public class NameValidator extends ConvertValidator<String> {

	private static final String NAME_REGEXP = "^[\\p{L}\\p{M} ]+$";

	/**
	 * Constructor with parameter
	 * 
	 * @param value - string value
	 */
	public NameValidator(String value) {
		super(value);
	}

	@Override
	public boolean isCastable() {
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(NAME_REGEXP);
		Matcher matcher = p.matcher(value);
		return matcher.find();
	}

	@Override
	public String getConverted() {
		return value;
	}
}
