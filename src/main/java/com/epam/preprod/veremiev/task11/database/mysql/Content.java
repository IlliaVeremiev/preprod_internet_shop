package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction content
 * 
 * @author Illia_Veremiev
 *
 * @param <E> - returnable type
 */
@FunctionalInterface
public interface Content<E> {

	/**
	 * Process code used to work with DB
	 * 	
	 * @param con - connection
	 * @return operation result
	 * @throws SQLException if error occurs
	 */
	E accept(Connection con) throws SQLException;
}
