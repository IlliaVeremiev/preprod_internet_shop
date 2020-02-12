package com.epam.preprod.veremiev.task11.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter disable browser caching
 */
public class DisableCachingFilter implements Filter {

	private static final String CACHE_CONTROL = "private, no-store, no-cache, must-revalidate";
	private static final String PRAGMA = "no-cache";

	/**
	 * Default constructor.
	 */
	public DisableCachingFilter() {

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
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Cache-Control", CACHE_CONTROL);
		res.setHeader("Pragma", PRAGMA);
		res.setDateHeader("Expires", 0);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
