package com.epam.preprod.veremiev.task11.core.adapters.errors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Collects text errors in representation of two Strings, header and body
 * 
 * @author Illia_Veremiev
 *
 */
public class ErrorsDispatcher implements Iterable<ErrorsDispatcher.Error> {

	/**
	 * List of errors
	 */
	private List<Error> errors = new ArrayList<>();

	/**
	 * Adds new error to container
	 * 
	 * @param errorHeader - error header
	 * @param message     - error message
	 */
	public void add(String errorHeader, String message) {
		errors.add(new Error(errorHeader, message));
	}

	/**
	 * Returns true if contains errors
	 * 
	 * @return true if contains errors
	 */
	public boolean contains() {
		return !errors.isEmpty();
	}

	@Override
	public Iterator<Error> iterator() {
		return errors.iterator();
	}

	/**
	 * Class wrapper that contains error header and body
	 * 
	 * @author Illia_Veremiev
	 *
	 */
	public class Error {

		/**
		 * Error header
		 */
		private String header;

		/**
		 * Error body
		 */
		private String message;

		/**
		 * Constructor with parameters
		 * 
		 * @param header  - error header
		 * @param message - error body
		 */
		private Error(String header, String message) {
			this.header = header;
			this.message = message;
		}

		/**
		 * @return the header
		 */
		public String getHeader() {
			return header;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
	}
}
