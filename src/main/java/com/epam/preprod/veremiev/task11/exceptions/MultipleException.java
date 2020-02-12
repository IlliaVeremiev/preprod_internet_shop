package com.epam.preprod.veremiev.task11.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiple exception. Contains list of {@link Exception} messages. Used for
 * collect information about many issues
 * 
 * @author Illia_Veremiev
 *
 */
public class MultipleException extends ServiceException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5931826660682438755L;

	/**
	 * Exception messages container
	 */
	private List<String> exceptions;

	public MultipleException() {
		exceptions = new ArrayList<>();
	}

	/**
	 * Adds exception to container
	 * 
	 * @param exceptionMessage - exception message to add
	 */
	public void addException(String exceptionMessage) {
		exceptions.add(exceptionMessage);
	}

	/**
	 * @return the exception messages
	 */
	public List<String> getExceptions() {
		return exceptions;
	}

	/**
	 * Returns true if contains exceptions
	 * 
	 * @return true if contains exceptions
	 */
	public boolean containsExceptions() {
		return !exceptions.isEmpty();
	}
}
