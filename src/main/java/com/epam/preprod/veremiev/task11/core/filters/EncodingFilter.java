package com.epam.preprod.veremiev.task11.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.epam.preprod.veremiev.task11.constants.FiltersAttributes;

/**
 * Encoding filter
 * 
 * @author Illia_Veremiev
 *
 */
public class EncodingFilter implements Filter {

	/**
	 * Encoding
	 */
	private String encoding;

	@Override
	public void init(FilterConfig fConfig) {
		encoding = fConfig.getInitParameter(FiltersAttributes.ENCODING);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}