package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.ManufacturerDAO;
import com.epam.preprod.veremiev.task11.model.entities.utils.Manufacturer;
import com.epam.preprod.veremiev.task11.model.parsers.db.products.ManufacturerDBPasrer;

/**
 * MYSQL {@link ManufacturerDAO} implementation
 * 
 * @author Illia_Veremiev
 *
 */
public class ManufacturerMYSQL implements ManufacturerDAO {

	@Override
	public List<Manufacturer> getManufacturers(Connection con) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.GET_MANUFACTURERS)) {
			List<Manufacturer> manufacturers = new ArrayList<>();
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					manufacturers.add(new ManufacturerDBPasrer().parse(rs));
				}
			}
			return manufacturers;
		} catch (SQLException e) {
			throw new SQLException(DBErrorMessages.CANT_GET_MANUFACTURERS_FROM_DB, e);
		} catch (Exception e) {
			throw new SQLException(DBErrorMessages.CANT_PARSE_MANUFACTURERS_FROM_DB, e);
		}
	}

	@Override
	public boolean create(Connection con, Manufacturer e) throws SQLException {
		return false;
	}

	@Override
	public boolean update(Connection con, Manufacturer e) throws SQLException {
		return false;
	}

	@Override
	public Manufacturer get(Connection con, long id) throws SQLException {
		return null;
	}

	@Override
	public boolean remove(Connection con, Manufacturer e) throws SQLException {
		return false;
	}
}
