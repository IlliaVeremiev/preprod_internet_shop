package com.epam.preprod.veremiev.task11.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.preprod.veremiev.task11.core.filters.utils.GzipServletResponseWrapper;

/**
 * Compressing filter. If browser accept gzip encoding, pack text response
 * 
 * @author Illia_Veremiev
 *
 */
public class CompressingFilter implements Filter {

	@Override
	public void init(FilterConfig fConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (acceptsGZipEncoding(req)) {
			GzipServletResponseWrapper responseWrapper = new GzipServletResponseWrapper(res);
			chain.doFilter(request, responseWrapper);
			responseWrapper.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean acceptsGZipEncoding(HttpServletRequest request) {
		String acceptEncoding = request.getHeader("Accept-Encoding");
		String accept = request.getHeader("Accept");
		return acceptEncoding != null && acceptEncoding.contains("gzip") && accept.contains("text");
	}

	@Override
	public void destroy() {
	}
}