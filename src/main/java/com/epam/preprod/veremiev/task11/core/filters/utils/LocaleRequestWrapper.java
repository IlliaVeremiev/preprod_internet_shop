package com.epam.preprod.veremiev.task11.core.filters.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Wrap HttpServletRequest, overrides {@link #getLocale()} method and adds
 * {@link #setLocale(Locale)} method
 * 
 * @author Illia_Veremiev
 *
 */
public class LocaleRequestWrapper extends HttpServletRequestWrapper {

	private Locale locale;

	public LocaleRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * Sets locale returned bu {@link #getLocale()}
	 * 
	 * @param locale - locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}
}
