package com.epam.preprod.veremiev.task11.core.servlets.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.RequestParameters;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.errors.ErrorsDispatcher;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.modules.captcha.Captcha;
import com.epam.preprod.veremiev.task11.modules.captcha.CaptchaDispatcher;

/**
 * {@link RegistrationServlet} unsuccessful test
 * 
 * @author Illia_Veremiev
 *
 */
public class RegistrationServletTest2 {

	@Test
	public void shouldReturnErrorMessageOnIncorrectPasswordInRequest() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);

		ErrorsDispatcher errorsDispatcher = new ErrorsDispatcher();
		when(session.getAttribute(SessionAttributes.ERRORS)).thenReturn(errorsDispatcher);
		when(request.getServletPath()).thenReturn("/registration");

		when(request.getParameter(RequestParameters.CAPTCHA)).thenReturn("ASD");
		when(request.getParameter(RequestParameters.CAPTCHA_NUMBER)).thenReturn("1");

		ServletContext mockServletContext = mock(ServletContext.class);
		when(request.getServletContext()).thenReturn(mockServletContext);

		CaptchaDispatcher captchaDispatcher = mock(CaptchaDispatcher.class);
		when(mockServletContext.getAttribute(ContextAttributes.CAPTCHA_DISPATCHER)).thenReturn(captchaDispatcher);

		User user = new User();
		user.setName("Ilya");
		user.setSurname("Veremiev");
		user.setLogin("ilya.v");
		user.setEmail("ilya.v@epam.com");
		/*
		 * Wrong password
		 */
		user.setPassword("asdQWE");
		user.setCountry("Ukraine");
		user.setMailing(new ArrayList<>());

		Map<String, String[]> map = new HashMap<>();
		map.put(RequestParameters.USER_NAME, new String[] { user.getName() });
		map.put(RequestParameters.USER_SURNAME, new String[] { user.getSurname() });
		map.put(RequestParameters.USER_LOGIN, new String[] { user.getLogin() });
		map.put(RequestParameters.USER_EMAIL, new String[] { user.getEmail() });
		map.put(RequestParameters.USER_PASSWORD, new String[] { user.getPassword() });
		map.put(RequestParameters.USER_REPASSWORD, new String[] { user.getPassword() });
		map.put(RequestParameters.USER_COUNTRY, new String[] { user.getCountry() });
		map.put(RequestParameters.USER_ACCEPT_TERMS, new String[] { "on" });
		map.put(RequestParameters.USER_MAILINGS, new String[] {});
		when(request.getParameterMap()).thenReturn(map);

		Captcha captcha = mock(Captcha.class);
		when(captchaDispatcher.get(session, 1)).thenReturn(captcha);
		when(captcha.getText()).thenReturn("ASD");
		when(captchaDispatcher.compare(session, "ASD", 1)).thenReturn(true);

		doAnswer((Answer) invocation -> {
			Object[] objects = invocation.getArguments();
			if (objects[0] == SessionAttributes.USER) {
				User u = (User) objects[1];
				u.setId(user.getId());
				Assert.assertEquals(user, u);
			}
			return null;
		}).when(session).setAttribute(any(String.class), any(User.class));
		new RegistrationServlet().doPost(request, response);
		verify(session, times(0)).setAttribute(SessionAttributes.USER, user);
	}
}
