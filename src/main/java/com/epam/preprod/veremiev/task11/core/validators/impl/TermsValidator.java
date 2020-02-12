package com.epam.preprod.veremiev.task11.core.validators.impl;

import com.epam.preprod.veremiev.task11.core.validators.ConvertValidator;

/**
 * Casts checkbox value to boolean
 *
 * @author Illia_Veremiev
 */
public class TermsValidator extends ConvertValidator<String>{

	/**
	 * Constructor with parameter
	 *
	 * @param value - string value
	 */
	public TermsValidator(String value){
		super(value);
	}

	@Override
	public boolean isCastable(){
		return value != null;
	}

	@Override
	public String getConverted(){
		return value;
	}
}
