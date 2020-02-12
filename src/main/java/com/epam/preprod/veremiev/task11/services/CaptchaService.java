package com.epam.preprod.veremiev.task11.services;

import javax.servlet.http.HttpSession;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.modules.captcha.Captcha;
import com.epam.preprod.veremiev.task11.modules.captcha.CaptchaDispatcher;

/**
 * Captcha service
 * 
 * @author Illia_Veremiev
 *
 */
public class CaptchaService {

	/**
	 * Validates {@link Captcha}
	 * 
	 * @param userInput         - user inputed captcha
	 * @param captchaId         - captcha id in container
	 * @param captchaDispatcher - captcha dispatcher
	 * @param session           - {@link HttpSession}
	 * @return true if user input equals equals captcha
	 * @throws ServiceException if errors occurs
	 */
	public boolean validate(String userInput, String captchaId, CaptchaDispatcher captchaDispatcher,
			HttpSession session) throws ServiceException {
		try {
			return captchaDispatcher.compare(session, userInput, Long.parseLong(captchaId));
		} catch (NumberFormatException e) {
			throw new ServiceException(ExceptionMessages.INVALID_CAPTCHA_INPUT);
		}
	}
}
