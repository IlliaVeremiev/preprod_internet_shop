package com.epam.preprod.veremiev.task11.core.servlets.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.ErrorHeaders;
import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.constants.RequestAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestParameters;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleServletContextAdapter;
import com.epam.preprod.veremiev.task11.core.adapters.SimpleSessionAdapter;
import com.epam.preprod.veremiev.task11.core.directions.Director;
import com.epam.preprod.veremiev.task11.core.directions.Director.METHOD;
import com.epam.preprod.veremiev.task11.core.timers.TimeredTask;
import com.epam.preprod.veremiev.task11.exceptions.MultipleException;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.files.FileManager;
import com.epam.preprod.veremiev.task11.model.bean.UserDataBean;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.modules.captcha.Captcha;
import com.epam.preprod.veremiev.task11.modules.captcha.CaptchaDispatcher;
import com.epam.preprod.veremiev.task11.services.CaptchaService;
import com.epam.preprod.veremiev.task11.services.FileService;
import com.epam.preprod.veremiev.task11.services.UserService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/registration")
@MultipartConfig(maxFileSize = RegistrationServlet.MAX_FILE_SIZE, maxRequestSize = RegistrationServlet.MAX_REQUEST_SIZE, fileSizeThreshold = RegistrationServlet.FILE_SIZE_THRESHOLD)
public class RegistrationServlet extends HttpServlet {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4255385321987879395L;

	protected static final int MAX_FILE_SIZE = 10 * 1024 * 1024;
	protected static final int MAX_REQUEST_SIZE = 20 * 1024 * 1024;
	protected static final int FILE_SIZE_THRESHOLD = 5 * 1024 * 1024;

	private UserService userService;

	private CaptchaService captchaService;

	private FileService fileService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) {
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(config.getServletContext());
		userService = contextAdapter.get(ContextAttributes.USER_SERVICE, UserService.class);
		captchaService = contextAdapter.get(ContextAttributes.CAPTCHA_SERVICE, CaptchaService.class);
		fileService = contextAdapter.get(ContextAttributes.FILE_SERVICE, FileService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(session);
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(request.getServletContext());
		CaptchaDispatcher captchaDispatcher = contextAdapter.get(ContextAttributes.CAPTCHA_DISPATCHER,
				CaptchaDispatcher.class);

		Captcha captcha = new Captcha();
		long captchaId = captchaDispatcher.save(session, captcha);
		request.setAttribute(RequestAttributes.CAPTCHA_ID, captchaId);
		request.setAttribute(RequestAttributes.CAPTCHA, captcha);
		sessionAdapter.removeTimeredTask(SessionAttributes.REGISTRATION_TIMEOUT);
		sessionAdapter.addTimeredTask(SessionAttributes.REGISTRATION_TIMEOUT, new TimeredTask(() -> {
			captchaDispatcher.remove(session, captchaId);
		}));
		int captchaLifetime = contextAdapter.get(ContextAttributes.CAPTCHA_LIFETIME, Integer.class);
		sessionAdapter.startTimeredTask(SessionAttributes.REGISTRATION_TIMEOUT, captchaLifetime * 1000);

		sessionAdapter.migrateErrorsTo(request);
		sessionAdapter.migrateTo(request, SessionAttributes.USER_DATA_BEAN);

		new Director(METHOD.FORWARD, "/WEB-INF/registration.jsp").now(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleSessionAdapter sessionAdapter = new SimpleSessionAdapter(session);
		ServletContext servletContext = request.getServletContext();
		SimpleServletContextAdapter contextAdapter = new SimpleServletContextAdapter(servletContext);
		CaptchaDispatcher captchaDispatcher = contextAdapter.get(ContextAttributes.CAPTCHA_DISPATCHER,
				CaptchaDispatcher.class);

		UserDataBean userDataBean = new UserDataBean(request.getParameterMap());

		String captchaInput = request.getParameter(RequestParameters.CAPTCHA);
		String captchaSNumber = request.getParameter(RequestParameters.CAPTCHA_NUMBER);

		try {
			if (!captchaService.validate(captchaInput, captchaSNumber, captchaDispatcher, session)) {
				sessionAdapter.addError(ErrorHeaders.REGISTRATION_ERROR, ExceptionMessages.INVALID_CAPTCHA_INPUT);
			} else {
				String filePath = FileManager.getComplexPath(servletContext.getRealPath("/"),
						contextAdapter.get(ContextAttributes.USER_ICON_PATH, String.class));
				String fileName = fileService.upload(request.getPart(RequestParameters.USER_PHOTO_PART), filePath);
				userDataBean.setPhoto(fileName);
				User user = userService.registrate(userDataBean);
				session.setAttribute(SessionAttributes.USER, user);
			}
		} catch (MultipleException e) {
			e.getExceptions().forEach((ex) -> sessionAdapter.addError(ErrorHeaders.INPUT_ERROR, ex));
		} catch (ServiceException e) {
			sessionAdapter.addError(ErrorHeaders.REGISTRATION_ERROR, e.getMessage());
		}

		if (sessionAdapter.isContainsError()) {
			sessionAdapter.setAttribute(SessionAttributes.USER_DATA_BEAN, userDataBean);
			Director.redirectGet(request, response);
		} else {
			new Director(METHOD.SEND_REDIRECT, "home").now(request, response);
		}
	}
}
