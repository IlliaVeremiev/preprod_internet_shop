package com.epam.preprod.veremiev.task11.utils.locale;

import javax.servlet.http.HttpSession;

/**
 * {@link ParametersContainable} implementation for session
 * 
 * @author Illia_Veremiev
 *
 */
public class LocaleSessionContainer implements ParametersContainable {

	private HttpSession session;

	public LocaleSessionContainer(HttpSession session) {
		this.session = session;
	}

	@Override
	public boolean contains(String name) {
		return session.getAttribute(name) != null;

	}

	@Override
	public String getValue(String name) {
		return session.getAttribute(name).toString();

	}

	@Override
	public void setValue(String name, String value, int lifetime) {
		session.setAttribute(name, value);
	}
}
