package com.epam.preprod.veremiev.task11.core.validators.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Email validator
 * 
 * @author Illia_Veremiev
 *
 */
public class EmailValidator extends ConvertValidator<String> {

	/*
	 * Email testing regular expression
	 */
	private static final String EMAIL_REGEXP = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

	/**
	 * Constructor with parameters
	 * 
	 * @param value - text to check
	 */
	public EmailValidator(String value) {
		super(value);
	}

	@Override
	public boolean isCastable() {
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(EMAIL_REGEXP);
		Matcher matcher = p.matcher(value);
		return matcher.find();
	}

	@Override
	public String getConverted() {
		return value;
	}
}