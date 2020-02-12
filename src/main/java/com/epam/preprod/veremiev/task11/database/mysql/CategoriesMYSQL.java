package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.CategoriesDAO;
import com.epam.preprod.veremiev.task11.model.entities.utils.Category;
import com.epam.preprod.veremiev.task11.model.parsers.db.products.CategoryDBPasrer;

/**
 * MYSQL {@link CategoriesDAO} implementation
 * 
 * @author Illia_Veremiev
 *
 */
public class CategoriesMYSQL implements CategoriesDAO {

	@Override
	public List<Category> getByIdWithChilds(Connection con, String id) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.GET_CHILD_CATEGORIES_BY_ID)) {
			ps.setString(1, id);
			List<Category> categories = new ArrayList<>();
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					categories.add(new CategoryDBPasrer().parse(rs));
				}
			}
			return categories;
		} catch (SQLException e) {
			throw new SQLException(DBErrorMessages.CANT_GET_CATEGORIES_FROM_DB, e);
		} catch (Exception e) {
			throw new SQLException(DBErrorMessages.CANT_PARSE_CATEGORIES_FROM_DB, e);
		}
	}

	@Override
	public boolean create(Connection con, Category e) throws SQLException {
		return false;
	}

	@Override
	public boolean update(Connection con, Category e) throws SQLException {
		return false;
	}

	@Override
	public Category get(Connection con, long id) throws SQLException {
		return null;
	}

	@Override
	public boolean remove(Connection con, Category e) throws SQLException {
		return false;
	}
}
