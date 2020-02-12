package com.epam.preprod.veremiev.task11.core.validators.impl;

import java.util.regex.Pattern;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Validates that password fit some rules
 * 
 * @author Illia_Veremiev
 *
 */
public class PasswordValidator extends ConvertValidator<String> {

	private static final String UPPERASE_REGEX = "[A-Z]";
	private static final String LOWERCASE_REGEX = "[a-z]";
	private static final String NUMBERS_REGEX = "[0-9]";

	/**
	 * Constructor with parameter
	 * 
	 * @param value - string value
	 */
	public PasswordValidator(String value) {
		super(value);
	}

	@Override
	public boolean isCastable() {
		if (value == null) {
			return false;
		}
		return isContainsLowercase() && isContainsNumbers() && isContainsUppercase();
	}

	private boolean isContainsLowercase() {
		return Pattern.compile(LOWERCASE_REGEX).matcher(value).find();
	}

	private boolean isContainsUppercase() {
		return Pattern.compile(UPPERASE_REGEX).matcher(value).find();
	}

	private boolean isContainsNumbers() {
		return Pattern.compile(NUMBERS_REGEX).matcher(value).find();
	}

	@Override
	public String getConverted() {
		return value;
	}
}