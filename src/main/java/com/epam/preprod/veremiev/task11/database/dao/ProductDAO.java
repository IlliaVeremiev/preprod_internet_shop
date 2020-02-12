package com.epam.preprod.veremiev.task11.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.services.utils.ExtendedSearchBean;

/**
 * ProductsDAO
 * 
 * @author Illia_Veremiev
 *
 */
public interface ProductDAO extends GenericDAO<InputDevice> {

	/**
	 * Returns {@link List} of input devices by sorting parameters
	 * 
	 * @param con           - Database Connection
	 * @param searchBean - bean to use
	 * @return {@link List} of input devices by sorting parameters
	 * @throws SQLException if errors occurs
	 */
	List<InputDevice> getBySortingRequest(Connection con, ExtendedSearchBean searchBean) throws SQLException;

	/**
	 * Returns products count by filters
	 * 
	 * @param con           - Database Connection
	 * @param searchBean - bean to use
	 * @return products count by filters
	 * @throws SQLException if errors occurs
	 */
	long getProductsCount(Connection con, ExtendedSearchBean searchBean) throws SQLException;
}
