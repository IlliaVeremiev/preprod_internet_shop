package com.epam.preprod.veremiev.task11.utils.attributable;

import javax.servlet.ServletContext;

/**
 * AttributableContaner for {@link ServletContext}
 * 
 * @author Illia_Veremiev
 *
 */
public class AttributableContext implements AttributableContaner {

	/**
	 * {@link ServletContext} to work
	 */
	private ServletContext context;

	/**
	 * Constructor with parameters
	 * 
	 * @param context - context to work
	 */
	public AttributableContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setAttribute(String name, Object value) {
		context.setAttribute(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return context.getAttribute(name);
	}

	@Override
	public void removeAttribute(String name) {
		context.removeAttribute(name);
	}
}
