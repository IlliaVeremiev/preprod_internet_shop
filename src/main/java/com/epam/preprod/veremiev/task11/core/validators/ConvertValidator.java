package com.epam.preprod.veremiev.task11.core.validators;

/**
 * Class that tests is string castable to <b>E</b>
 * 
 * @author Illia_Veremiev
 *
 * @param <E> - class in which need to cast
 */
public abstract class ConvertValidator<E> {

	/**
	 * string value
	 */
	protected String value;

	/**
	 * Default constructor
	 * 
	 * @param value - text value
	 */
	public ConvertValidator(String value) {
		this.value = value;
	}

	/**
	 * Returns true if possible to cast <b>value</b> to <b>E</b>
	 * 
	 * @return true if possible to cast <b>value</b> to <b>E</b>
	 */
	public abstract boolean isCastable();

	/**
	 * Returns value casted to <b>E</b>
	 * 
	 * @return value casted to <b>E</b>
	 */
	public abstract E getConverted();
}
