package com.epam.preprod.veremiev.task11.utils.attributable;

import javax.servlet.http.HttpSession;

/**
 * AttributableContaner for {@link HttpSession}
 * 
 * @author Illia_Veremiev
 *
 */
public class AttributableSession implements AttributableContaner {

	/**
	 * {@link HttpSession} to work
	 */
	private HttpSession session;

	/**
	 * Constructor with parameters
	 * 
	 * @param session - session to work
	 */
	public AttributableSession(HttpSession session) {
		this.session = session;
	}

	@Override
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return session.getAttribute(name);
	}

	@Override
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}
}
