package com.epam.preprod.veremiev.task11.database.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Connection pool
 * 
 * @author Illia_Veremiev
 *
 */
public class ConnectionPool {

	private static final Logger log = Logger.getLogger(ConnectionPool.class);

	private static final String INIT_CONTEXT_LOOKUP = "java:/comp/env";
	private static final String ENV_CONTEXT_LOOKUP = "jdbc/internet_shop";

	private static ConnectionPool instance;

	private ConnectionPool() {
		initContext();
	}

	/**
	 * Data source
	 */
	private DataSource ds;

	/**
	 * Returns instance of {@link ConnectionPool}. Only one instance for all calls.
	 * 
	 * @return instance of {@link ConnectionPool}
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	private void initContext() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(INIT_CONTEXT_LOOKUP);
			ds = (DataSource) envContext.lookup(ENV_CONTEXT_LOOKUP);
		} catch (NamingException ex) {
			log.warn(ex.getMessage());
		}
	}

	/**
	 * Returns connection from connection pool
	 * 
	 * @return connection from connection pool
	 * @throws SQLException - if errors occurs
	 */
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
