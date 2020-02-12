package com.epam.preprod.veremiev.task11.core.servlets.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.ErrorHeaders;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.bean.UserDataBean;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5600011373752700125L;

	private UserService userService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(config.getServletContext());
		userService = contextAdapter.get(ContextAttributes.USER_SERVICE, UserService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(session);

		sessionAdapter.migrateErrorsTo(request);
		sessionAdapter.migrateTo(request, SessionAttributes.USER_DATA_BEAN);
		new Director(METHOD.FORWARD, "/WEB-INF/login.jsp").now(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(session);
		UserDataBean userDataBean = new UserDataBean(request.getParameterMap());
		try {
			User user = userService.login(userDataBean);
			session.setAttribute(SessionAttributes.USER, user);
		} catch (ServiceException e) {
			sessionAdapter.addError(ErrorHeaders.LOGIN_ERROR, e.getMessage());
		}
		if (sessionAdapter.isContainsError()) {
			sessionAdapter.setAttribute(SessionAttributes.USER_DATA_BEAN, userDataBean);
			Director.redirectGet(request, response);
		} else {
			priorityRedirect(request, response);
		}
	}

	private void priorityRedirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirect = request.getParameter("redirect");
		if (redirect == null || redirect.isEmpty()) {
			new Director(METHOD.SEND_REDIRECT, "home").now(request, response);
		} else {
			new Director(METHOD.SEND_REDIRECT, redirect).now(request, response);
		}
	}
}
