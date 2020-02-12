package com.epam.preprod.veremiev.task11.core.adapters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.utils.locale.ParametersContainable;

/**
 * Provides some logic to work with cookies
 * 
 * @author Illia_Veremiev
 *
 */
public class CookieAdapter implements ParametersContainable {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public CookieAdapter(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * Returns index of cookie with name <b>name</b>
	 * 
	 * @param name - cookie name
	 * @return index of cookie with name <b>name</b>
	 */
	public int indexOf(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return -1;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean contains(String name) {
		return indexOf(name) != -1;
	}

	@Override
	public void setValue(String name, String value, int lifetime) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(lifetime);
		response.addCookie(cookie);
	}

	@Override
	public String getValue(String name) {
		return request.getCookies()[indexOf(name)].getValue();
	}
}
