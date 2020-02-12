package com.epam.preprod.veremiev.task11.modules.captcha;

import java.util.HashMap;
import java.util.Map;

/**
 * Captcha container
 * 
 * @author Illia_Veremiev
 *
 */
public class CaptchaContainer {

	/**
	 * Id iterator
	 */
	private long id;

	/**
	 * Container
	 */
	private Map<Long, Captcha> map = new HashMap<>();

	/**
	 * Adds new captcha to map
	 * 
	 * @param captcha - captcha to add
	 * @return captcha id
	 */
	public long push(Captcha captcha) {
		map.put(++id, captcha);
		return id;
	}

	/**
	 * Remove captcha from map and returns it
	 * 
	 * @param id - capthca id
	 * @return captcha by id
	 */
	public Captcha pull(Long id) {
		Captcha captcha = map.get(id);
		map.remove(id);
		return captcha;
	}
}