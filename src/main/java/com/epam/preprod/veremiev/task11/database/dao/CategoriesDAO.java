package com.epam.preprod.veremiev.task11.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.preprod.veremiev.task11.model.entities.utils.Category;

/**
 * CategoriesDAO
 * 
 * @author Illia_Veremiev
 *
 */
public interface CategoriesDAO extends GenericDAO<Category> {

	/**
	 * Returns category and child categories
	 * 
	 * @param con - database connection
	 * @param id  - parent category id
	 * @return category and child categories
	 * @throws SQLException if errors occurs
	 */
	List<Category> getByIdWithChilds(Connection con, String id) throws SQLException;
}
