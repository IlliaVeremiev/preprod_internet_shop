package com.epam.preprod.veremiev.task11.exceptions;

/**
 * Service exception
 * 
 * @author Illia_Veremiev
 *
 */
public class ServiceException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7928742595647087555L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
