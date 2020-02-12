package com.epam.preprod.veremiev.task11.database.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.model.entities.User;

/**
 * UserDAO
 * 
 * @author Illia_Veremiev
 *
 */
public interface UserDAO extends GenericDAO<User> {

	/**
	 * Returns user by login
	 * 
	 * @param con   - Database Connection
	 * @param login - user login
	 * @return user by login
	 * @throws SQLException if errors occurs
	 */
	User getByLogin(Connection con, String login) throws SQLException;
}
