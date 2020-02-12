package com.epam.preprod.veremiev.task11.database.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.model.entities.AbstractEntity;

/**
 * Abstract DAO
 * 
 * @author Illia_Veremiev
 *
 * @param <E> - entity
 */
public interface GenericDAO<E extends AbstractEntity> {

	/**
	 * Adds e to database
	 * 
	 * @param e   - item to add
	 * @param con - connection to DB
	 * @return true if operation success
	 * @throws SQLException if error occurs
	 */
	boolean create(Connection con, E e) throws SQLException;

	/**
	 * Update e in database
	 * 
	 * @param e - item to update
	 * @return true if operation success
	 * @param con - connection to DB
	 * @throws SQLException if error occurs
	 */
	boolean update(Connection con, E e) throws SQLException;

	/**
	 * Select item from database
	 * 
	 * @param id  - by which select
	 * @param con - connection to DB
	 * @return founded instance
	 * @throws SQLException if error occurs
	 */
	E get(Connection con, long id) throws SQLException;

	/**
	 * Removes e from database
	 * 
	 * @param e   - item to remove
	 * @param con - connection to DB
	 * @return true if operation success
	 * @throws SQLException if error occurs
	 */
	boolean remove(Connection con, E e) throws SQLException;
}
