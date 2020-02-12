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

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestParameters;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.core.filters.utils.security.entities.AccessRights;
import com.epam.preprod.veremiev.task11.model.entities.User;

/**
 * Provide access to pages by user roles
 * 
 * @author Illia_Veremiev
 *
 */
public class AccessRightsFilter implements Filter {

	private AccessRights accessRights;

	@Override
	public void init(FilterConfig fConfig) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(fConfig.getServletContext());
		accessRights = contextAdapter.get(ContextAttributes.ACCESS_RIGHTS, AccessRights.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (!passByCheck(req, res, chain)) {
			if (userLoggedIn(req, res)) {
				if (!userHasAccess(req, res, chain)) {
					new Director(METHOD.FORWARD, "/WEB-INF/access-error.jsp").now(req, res);
				}
			}
		}
	}

	public boolean userHasAccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		User user = sessionAdapter.get(SessionAttributes.USER, User.class);
		if (accessRights.hasAccess(user.getRole(), request.getServletPath())) {
			chain.doFilter(request, response);
			return true;
		}
		return false;
	}

	public boolean passByCheck(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!accessRights.fitURL(request.getServletPath())) {
			chain.doFilter(request, response);
			return true;
		}
		return false;
	}

	public boolean userLoggedIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(request.getSession());
		User user = sessionAdapter.get(SessionAttributes.USER, User.class);
		if (user == null) {
			new Director(METHOD.SEND_REDIRECT, "login")
					.addParameter(RequestParameters.REDIRECT, request.getServletPath().substring(1))
					.now(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void destroy() {
	}
}