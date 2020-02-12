package com.epam.preprod.veremiev.task11.core.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestParameters;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.filters.utils.LocaleRequestWrapper;
import com.epam.preprod.veremiev.task11.utils.locale.LocaleContainableFactory;
import com.epam.preprod.veremiev.task11.utils.locale.ParametersContainable;

/**
 * Filter manages user selected language
 */
public class LocaleFilter implements Filter {

	private static final Logger log = Logger.getLogger(LocaleFilter.class);

	private List<Locale> allowedLocales;

	private Locale defaultLocale;

	private LocaleContainableFactory containableFactory;

	private int cookieLifeTime;

	/**
	 * Default constructor.
	 */
	public LocaleFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("Start LocaleFilter#doFilter");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(req.getSession());
		LocaleRequestWrapper localeRequestWrapper = new LocaleRequestWrapper(req);
		ParametersContainable parametersContainable = containableFactory.build(req, res);

		if (!setByRequestParameter(localeRequestWrapper, parametersContainable)) {
			if (!setByContainer(localeRequestWrapper, parametersContainable)) {
				if (!setMoreSuitableForUser(localeRequestWrapper)) {
					localeRequestWrapper.setLocale(defaultLocale);
				}
			}
		}

		if (!sessionAdapter.contains(SessionAttributes.CURRENT_LOCALE)) {
			req.setAttribute(SessionAttributes.CURRENT_LOCALE, localeRequestWrapper.getLocale());
		}

		chain.doFilter(localeRequestWrapper, response);
		log.debug("End LocaleFilter#doFilter");
	}

	/**
	 * Returns true if request contains parameter {@link RequestParameters#LANG}
	 * 
	 * @param localeRequestWrapper  - request
	 * @param parametersContainable - container
	 * @return true if request contains parameter {@link RequestParameters#LANG}
	 */
	private boolean setByRequestParameter(LocaleRequestWrapper localeRequestWrapper,
			ParametersContainable parametersContainable) {
		String lang = localeRequestWrapper.getParameter(RequestParameters.LANG);
		if (lang != null && !lang.isEmpty()) {
			parametersContainable.setValue(SessionAttributes.LOCALE, lang, cookieLifeTime);
			localeRequestWrapper.setLocale(new Locale(lang));
			localeRequestWrapper.getSession().setAttribute(SessionAttributes.CURRENT_LOCALE, lang);
			return true;
		}
		return false;
	}

	/**
	 * Returns true if locale already setted
	 * 
	 * @param localeRequestWrapper  - request
	 * @param parametersContainable - container
	 * @return true if locale already setted
	 */
	private boolean setByContainer(LocaleRequestWrapper localeRequestWrapper,
			ParametersContainable parametersContainable) {
		if (parametersContainable.contains(SessionAttributes.LOCALE)) {
			localeRequestWrapper.setLocale(new Locale(parametersContainable.getValue(SessionAttributes.LOCALE)));
			return true;
		}
		return false;
	}

	/**
	 * Returns true if find suitable locale for user browser
	 * 
	 * @param localeRequestWrapper - request
	 * @return true if find suitable locale for user browser
	 */
	private boolean setMoreSuitableForUser(LocaleRequestWrapper localeRequestWrapper) {
		List<Locale> userLocales = Collections.list(localeRequestWrapper.getLocales());
		userLocales.retainAll(allowedLocales);
		if (userLocales.size() > 0) {
			localeRequestWrapper.setLocale(userLocales.get(0));
			return true;
		}
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String[] availableLocales = fConfig.getInitParameter(ContextAttributes.AVAILABLE_LOCALES).split(" ");
		allowedLocales = Arrays.stream(availableLocales).map(Locale::new).collect(Collectors.toList());
		fConfig.getServletContext().setAttribute(ContextAttributes.LOCALES, allowedLocales);
		defaultLocale = new Locale(fConfig.getInitParameter(ContextAttributes.DEFAULT_LOCALE));

		containableFactory = new LocaleContainableFactory(
				fConfig.getInitParameter(ContextAttributes.LOCALES_CONTAINER));
		String stringLifeTime = fConfig.getInitParameter(ContextAttributes.LOCALES_COOKIE_LIFETIME);
		cookieLifeTime = Integer.parseInt(stringLifeTime);
	}
}
