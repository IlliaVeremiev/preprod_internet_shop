package com.epam.preprod.veremiev.task11.core.validators.impl;

/**
 * Validates that both passwords are the same
 * 
 * @author Illia_Veremiev
 *
 */
public class RePasswordValidator extends PasswordValidator {

	/**
	 * Password
	 */
	private String password;

	/**
	 * Constructor with parameter
	 * 
	 * @param repassword - repeated password
	 * @param password   - password
	 */
	public RePasswordValidator(String repassword, String password) {
		super(repassword);
		this.password = password;
	}

	@Override
	public boolean isCastable() {
		if (value == null) {
			return false;
		}
		return value.equals(password);
	}

	@Override
	public String getConverted() {
		return value;
	}
}
