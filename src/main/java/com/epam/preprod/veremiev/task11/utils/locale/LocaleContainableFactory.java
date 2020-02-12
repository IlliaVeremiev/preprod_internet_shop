package com.epam.preprod.veremiev.task11.utils.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.core.adapters.CookieAdapter;

/**
 * {@link ParametersContainable} builder
 * 
 * @author Illia_Veremiev
 *
 */
public class LocaleContainableFactory {

	private String name;

	public LocaleContainableFactory(String name) {
		this.name = name;
	}

	/**
	 * Returns new instance of {@link ParametersContainable} depending on
	 * {@link #name}
	 * 
	 * @param request  - current request
	 * @param response - current response
	 * @return new instance of {@link ParametersContainable} depending on
	 *         {@link #name}
	 */
	public ParametersContainable build(HttpServletRequest request, HttpServletResponse response) {
		if ("session".equals(name)) {
			return new LocaleSessionContainer(request.getSession());
		}
		if ("cookies".equals(name)) {
			return new CookieAdapter(request, response);
		}
		throw new IllegalStateException();
	}
}
