package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.javalite.activejdbc.DBException;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.OrdersDAO;
import com.epam.preprod.veremiev.task11.model.entities.Order;

/**
 * {@link OrdersDAO} MYSQL implementation
 * 
 * @author Illia_Veremiev
 *
 */
public class OrdersMYSQL implements OrdersDAO {

	@Override
	public boolean create(Connection con, Order o) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, o.getStatus().toValue());
			ps.setString(k++, o.getDetalis());
			ps.setTimestamp(k++, o.getDate());
			ps.setLong(k++, o.getUserId());
			ps.setString(k++, o.getPayType());
			ps.setString(k++, o.getPayRequisites());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				o.setId(rs.getLong("GENERATED_KEY"));
			}
			return true;
		} catch (SQLException e) {
			throw new DBException(DBErrorMessages.CANT_ADD_ORDER_TO_DB, e);
		}
	}

	@Override
	public boolean update(Connection con, Order e) throws SQLException {
		return false;
	}

	@Override
	public Order get(Connection con, long id) throws SQLException {
		return null;
	}

	@Override
	public boolean remove(Connection con, Order e) throws SQLException {
		return false;
	}
}
