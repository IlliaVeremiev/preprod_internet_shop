package com.epam.preprod.veremiev.task11.database.utils;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * Connection utils
 * 
 * @author Illia_Veremiev
 *
 */
public class ConnectionUtils {

	private static final Logger log = Logger.getLogger(ConnectionUtils.class);

	/**
	 * Close connection
	 * 
	 * @param con - connection to close
	 */
	public static void close(Connection con) {
		if (con == null) {
			return;
		}
		try {
			con.close();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
	}
}
