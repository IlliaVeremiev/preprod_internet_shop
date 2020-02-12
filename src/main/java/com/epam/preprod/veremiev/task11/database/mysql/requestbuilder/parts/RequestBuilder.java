package com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts;

import org.apache.log4j.Logger;

/**
 * Collect request from many parts
 * 
 * @author Illia_Veremiev
 *
 */
public class RequestBuilder {

	private static final Logger log = Logger.getLogger(RequestBuilder.class);

	/**
	 * Request to work
	 */
	private String reqest;

	public RequestBuilder(String reqest) {
		this.reqest = reqest;
	}

	/**
	 * Sets <b>data</b> instead of <b>marker</b>
	 * 
	 * @param marker - marker to replace
	 * @param data   - data to set
	 */
	public void setParameter(String marker, String data) {
		reqest = reqest.replace(marker, data);
	}

	/**
	 * Sets LIMIT instead of <b>marker</b>
	 * 
	 * @param marker - marker to replace
	 * @param from   - from element
	 * @param to     - to element
	 */
	public void setLimit(String marker, String from, String to) {
		long page = 0;
		long count = 10;
		try {
			count = Long.parseLong(to);
			page = (Long.parseLong(from) - 1) * count;
		} catch (NumberFormatException e) {
			log.trace("Error convert pagenumber to number");
		}
		reqest = reqest.replace(marker, "LIMIT " + page + ", " + count);
	}

	@Override
	public String toString() {
		return reqest;
	}
}
