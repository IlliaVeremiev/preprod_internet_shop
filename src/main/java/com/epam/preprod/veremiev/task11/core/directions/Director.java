package com.epam.preprod.veremiev.task11.core.directions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Specific class to simplify redirection between pages
 *
 * @author Illia_Veremiev
 */
public class Director {

	/**
	 * Redirect methods
	 *
	 * @author Illia_Veremiev
	 */
	public enum METHOD {
		FORWARD, SEND_REDIRECT, INCLUDE
	}

	/**
	 * Redirect method
	 */
	private METHOD method;

	/**
	 * Redirect path
	 */
	private String path;

	private Map<String, String> parameters;

	/**
	 * Constructor with parameters
	 *
	 * @param method - redirect method
	 * @param path   - redirect path
	 */
	public Director(METHOD method, String path) {
		this.path = path;
		this.method = method;
		parameters = new HashMap<>();
	}

	/**
	 * Adds parameter to url
	 * 
	 * @param name  - parameter name
	 * @param value - parameter value
	 * @return current instance
	 */
	public Director addParameter(String name, String value) {
		parameters.put(name, value);
		return this;
	}

	/**
	 * Send redirect or forward request depending on <b>method</b>
	 *
	 * @param request  - {@link HttpServletRequest}
	 * @param response - {@link HttpServletResponse}
	 * @throws ServletException if the target resource throws this exception
	 * @throws IOException      if IO errors occurs
	 */
	public void now(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (method) {
		case INCLUDE:
		case FORWARD: {
			request.getRequestDispatcher(path).forward(request, response);
			break;
		}
		case SEND_REDIRECT:
		default: {
			String fullPath = path;
			if (!parameters.isEmpty()) {
				fullPath += "?" + parameters.entrySet().stream().map(i -> i.getKey() + "=" + i.getValue())
						.collect(Collectors.joining("&"));
			}
			response.sendRedirect(fullPath);
			break;
		}
		}
	}

	/**
	 * Send redirect on current page
	 *
	 * @param request  - {@link HttpServletRequest}
	 * @param response - {@link HttpServletResponse}
	 * @throws IOException if IO errors occurs
	 */
	public static void redirectGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getServletPath().replace("/", ""));
	}
}
