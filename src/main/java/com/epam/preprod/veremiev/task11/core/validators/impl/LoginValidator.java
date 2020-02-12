package com.epam.preprod.veremiev.task11.core.validators.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Login validator
 * 
 * @author Illia_Veremiev
 *
 */
public class LoginValidator extends ConvertValidator<String> {

	private static final int MIN_LOGIN_LENGTH = 4;
	private static final String MIN_LOGIN_LENGTH_STRING = String.valueOf(MIN_LOGIN_LENGTH - 2);
	private static final int MAX_LOGIN_LENGTH = 32;
	private static final String MAX_LOGIN_LENGTH_STRING = String.valueOf(MAX_LOGIN_LENGTH - 2);

	private static final String LOGIN_REGEXP = "^[a-zA-Z]([a-zA-Z0-9\\.](?<!\\.\\.)){" + MIN_LOGIN_LENGTH_STRING + ","
			+ MAX_LOGIN_LENGTH_STRING + "}[a-zA-Z0-9]$";

	/**
	 * Constructor with parameter
	 * 
	 * @param value - string value
	 */
	public LoginValidator(String value) {
		super(value);
	}

	@Override
	public boolean isCastable() {
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(LOGIN_REGEXP);
		Matcher matcher = p.matcher(value);
		return matcher.find();
	}

	@Override
	public String getConverted() {
		return value;
	}
}