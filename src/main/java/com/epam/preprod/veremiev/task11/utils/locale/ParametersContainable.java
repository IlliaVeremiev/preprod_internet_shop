package com.epam.preprod.veremiev.task11.utils.locale;

/**
 * Wrapper for container without interfaces
 * 
 * @author Illia_Veremiev
 *
 */
public interface ParametersContainable {

	/**
	 * Returns true if container exists item with <b>name</b>
	 * 
	 * @param name - parameter name
	 * @return true if container exists item with <b>name</b>
	 */
	boolean contains(String name);

	/**
	 * Returns parameter value
	 * 
	 * @param name - parameter name
	 * @return parameter value
	 */
	String getValue(String name);

	/**
	 * Adds new parameter to parameters list if not exists otherwise replace value
	 * 
	 * @param name     - parameter name
	 * @param value    - parameter value
	 * @param lifetime - parameter lifetime if it's cookie
	 * @return new parameter to parameters list
	 */
	void setValue(String name, String value, int lifetime);
}
