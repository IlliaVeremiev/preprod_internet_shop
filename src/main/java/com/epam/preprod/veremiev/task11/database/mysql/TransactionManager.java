package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.database.controller.ConnectionPool;
import com.epam.preprod.veremiev.task11.database.utils.ConnectionUtils;

/**
 * Transaction manager. Provides possibility to make request in transaction
 * easier
 * 
 * @author Illia_Veremiev
 *
 */
public class TransactionManager {

	/**
	 * Runs <b>context</b> in transaction
	 * 
	 * @param <E>     - returnable type
	 * @param content - transaction request body
	 * @return some answer from body
	 * @throws SQLException if errors occurs
	 */
	public <E> E doInTransaction(Content<E> content) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			con.setAutoCommit(false);
			E e = content.accept(con);
			con.commit();
			return e;
		} catch (SQLException e) {
			if (con != null) {
				con.rollback();
			}
			throw new SQLException(DBErrorMessages.DATABASE_ERROR, e);
		} finally {
			ConnectionUtils.close(con);
		}
	}
}
