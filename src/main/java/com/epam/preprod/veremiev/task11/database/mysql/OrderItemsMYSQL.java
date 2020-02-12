package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.javalite.activejdbc.DBException;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.OrderItemsDAO;
import com.epam.preprod.veremiev.task11.model.entities.OrderedInputDevice;

/**
 * {@link OrderItemsDAO} MYSQL implementation
 * 
 * @author Illia_Veremiev
 *
 */
public class OrderItemsMYSQL implements OrderItemsDAO {

	@Override
	public boolean create(Connection con, OrderedInputDevice d) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.INSERT_ORDER_ITEM)) {
			int k = 1;
			ps.setLong(k++, d.getOrderId());
			ps.setLong(k++, d.getDeviceId());
			ps.setBigDecimal(k++, d.getOldPrice());
			ps.setLong(k, d.getOrderedCount());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DBException(DBErrorMessages.CANT_ADD_ORDER_TO_DB, e);
		}
	}

	@Override
	public boolean update(Connection con, OrderedInputDevice e) throws SQLException {
		return false;
	}

	@Override
	public OrderedInputDevice get(Connection con, long id) throws SQLException {
		return null;
	}

	@Override
	public boolean remove(Connection con, OrderedInputDevice e) throws SQLException {
		return false;
	}
}
