package com.epam.preprod.veremiev.task11.utils.attributable;

/**
 * Attributes container. Wrapper for classes with same methods, but not subject
 * to one interface
 * 
 * @author Illia_Veremiev
 *
 */
public interface AttributableContaner {

	/**
	 * Sets attribute to container
	 * 
	 * @param name  - param name
	 * @param value - value
	 */
	void setAttribute(String name, Object value);

	/**
	 * Returns param value by name
	 * 
	 * @param name - name of param
	 * @return param value by name
	 */
	Object getAttribute(String name);

	/**
	 * Removes param by name
	 * 
	 * @param name - name of param
	 */
	void removeAttribute(String name);
}
