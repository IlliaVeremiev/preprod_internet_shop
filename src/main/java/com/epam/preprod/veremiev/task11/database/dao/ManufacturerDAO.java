package com.epam.preprod.veremiev.task11.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;

/**
 * Manufacturers DAO
 * 
 * @author Illia_Veremiev
 *
 */
public interface ManufacturerDAO extends GenericDAO<Manufacturer> {

	/**
	 * Returns manufacturers list
	 * 
	 * @param con - database connection
	 * @return manufacturers list
	 * @throws SQLException if errors occurs
	 */
	List<Manufacturer> getManufacturers(Connection con) throws SQLException;
}
