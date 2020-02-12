package com.epam.preprod.veremiev.task11.core.adapters;

import javax.servlet.ServletContext;

/**
 * Adapter for servlet context modification
 * 
 * @author Illia_Veremiev
 *
 */
public class SimpleServletContextAdapter {

	/**
	 * Servlet context to work
	 */
	private ServletContext context;

	/**
	 * Constructor with parameters
	 * 
	 * @param context - context to work
	 */
	public SimpleServletContextAdapter(ServletContext context) {
		this.context = context;
	}

	/**
	 * Returns attribute from <b>context</b> casted to E
	 * 
	 * @param <E>   - class in which to cast
	 * @param name  - name of param
	 * @param clazz - class in which to cast
	 * @return attribute from <b>context</b> casted to E
	 */
	public <E> E get(String name, Class<E> clazz) {
		return (E) context.getAttribute(name);
	}
}