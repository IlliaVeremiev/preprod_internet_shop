package com.epam.preprod.veremiev.task11.modules.captcha;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.epam.preprod.veremiev.task11.constants.ApplicationErrors;
import com.epam.preprod.veremiev.task11.constants.ContextAttributes;
import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.utils.attributable.AttributableContaner;
import com.epam.preprod.veremiev.task11.utils.attributable.AttributableContext;
import com.epam.preprod.veremiev.task11.utils.attributable.AttributableSession;

/**
 * Captcha dispatcher
 * 
 * @author Illia_Veremiev
 *
 */
public class CaptchaDispatcher {

	/**
	 * Captcha container name
	 */
	private String containerName;

	/**
	 * Captcha container
	 */
	private AttributableContaner container;

	/**
	 * Constructor with parameters
	 * 
	 * @param containerName  - container name
	 * @param servletContext - servler context
	 */
	public CaptchaDispatcher(String containerName, ServletContext servletContext) {
		this.containerName = containerName;
		container = new AttributableContext(servletContext);
	}

	private AttributableContaner getSuitableContainer(HttpSession session) {
		if (ContextAttributes.CAPTCHA_LOCATION_SESSION.equals(containerName)) {
			return new AttributableSession(session);
		}
		if (ContextAttributes.CAPTCHA_LOCATION_CONTEXT.contentEquals(containerName)) {
			return container;
		}
		throw new IllegalStateException(ApplicationErrors.INVALID_CAPTCHA_LOCATION);
	}

	private CaptchaContainer getCaptchaContainer(AttributableContaner container) {
		CaptchaContainer captchaContainer = (CaptchaContainer) container
				.getAttribute(ContextAttributes.CAPTCHA_CONTAINER);
		if (captchaContainer == null) {
			captchaContainer = new CaptchaContainer();
			container.setAttribute(ContextAttributes.CAPTCHA_CONTAINER, captchaContainer);
		}
		return captchaContainer;
	}

	/**
	 * Removes captcha instance from container
	 * 
	 * @param session - {@link HttpSession}
	 * @param id      - captcha id
	 */
	public void remove(HttpSession session, long id) {
		CaptchaContainer captchaContainer = getCaptchaContainer(getSuitableContainer(session));
		captchaContainer.pull(id);
	}

	/**
	 * Saves captcha to container
	 * 
	 * @param session - {@link HttpSession}
	 * @param captcha - captcha to save
	 * @return id in container
	 */
	public long save(HttpSession session, Captcha captcha) {
		CaptchaContainer captchaContainer = getCaptchaContainer(getSuitableContainer(session));
		return captchaContainer.push(captcha);
	}

	/**
	 * Returns captcha by id
	 * 
	 * @param session - {@link HttpSession}
	 * @param id      - captcha id
	 * @return captcha by id
	 */
	public Captcha get(HttpSession session, long id) {
		CaptchaContainer captchaContainer = getCaptchaContainer(getSuitableContainer(session));
		return captchaContainer.pull(id);
	}

	/**
	 * Comparing userInput and saved captcha data
	 * 
	 * @param session       - {@link HttpSession}
	 * @param userInput     - user input
	 * @param captchaNumber - captcha id
	 * @return true if captchas the same
	 * @throws ServiceException if errors occurs
	 */
	public boolean compare(HttpSession session, String userInput, long captchaNumber) throws ServiceException {
		Captcha captcha = get(session, captchaNumber);
		if (captcha == null) {
			throw new ServiceException(ExceptionMessages.DATA_IS_OUT_OF_DATE);
		}
		return captcha.getText().equals(userInput);
	}
}