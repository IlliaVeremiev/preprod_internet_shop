package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.ProductDAO;
import com.epam.preprod.veremiev.task11.database.mysql.requestbuilder.parts.RequestBuilder;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.model.parsers.db.products.InputDeviceDBPasrer;
import com.epam.preprod.veremiev.task11.services.utils.ExtendedSearchBean;

/**
 * MYSQL {@link ProductDAO} implementation
 * 
 * @author Illia_Veremiev
 *
 */
public class ProductMYSQL implements ProductDAO {

	@Override
	public boolean create(Connection con, InputDevice e) throws SQLException {
		return false;
	}

	@Override
	public boolean update(Connection con, InputDevice e) throws SQLException {
		return false;
	}

	@Override
	public InputDevice get(Connection con, long id) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.GET_PRODUCT_BY_ID)) {
			ps.setLong(1, id);
			InputDevice inputDevice = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				inputDevice = new InputDeviceDBPasrer().parse(rs);
			}
			return inputDevice;
		} catch (SQLException e) {
			throw new SQLException(DBErrorMessages.CANT_GET_PRODUCTS_FROM_DB, e);
		} catch (Exception e) {
			throw new SQLException(DBErrorMessages.CANT_PARSE_PRODUCTS_FROM_DB, e);
		}
	}

	@Override
	public boolean remove(Connection con, InputDevice e) throws SQLException {
		return false;
	}

	@Override
	public List<InputDevice> getBySortingRequest(Connection con, ExtendedSearchBean bean) throws SQLException {
		RequestBuilder builder = new RequestBuilder(MYSQLRequests.GET_PRODUCTS_BY_FILTERS);
		builder.setParameter(MYSQLRequests.ORDER_DIR_MARKER, "1".equals(bean.getOrderDir()) ? "ASC" : "DESC");
		builder.setParameter(MYSQLRequests.WHERE_MARKER, bean.getConditions().stream().filter(c -> c != null)
				.map(c -> " AND " + c).collect(Collectors.joining()));
		builder.setLimit(MYSQLRequests.LIMIT_MARKER, bean.getPageNumber(), bean.getPageItemsCount());
		try (PreparedStatement ps = con.prepareStatement(builder.toString())) {
			int k = 1;
			ps.setString(k++, "%" + (bean.getName() == null ? "" : bean.getName()) + "%");
			ps.setString(k++, bean.getOrderBy());
			List<InputDevice> inputDevices = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				inputDevices.add(new InputDeviceDBPasrer().parse(rs));
			}
			return inputDevices;
		} catch (SQLException e) {
			throw new SQLException(DBErrorMessages.CANT_GET_PRODUCTS_FROM_DB, e);
		} catch (Exception e) {
			throw new SQLException(DBErrorMessages.CANT_PARSE_PRODUCTS_FROM_DB, e);
		}
	}

	@Override
	public long getProductsCount(Connection con, ExtendedSearchBean bean) throws SQLException {
		RequestBuilder builder = new RequestBuilder(MYSQLRequests.GET_PRODUCTS_COUNT_BY_FILTERS);
		builder.setParameter(MYSQLRequests.WHERE_MARKER, bean.getConditions().stream().filter(c -> c != null)
				.map(c -> " AND " + c).collect(Collectors.joining()));
		try (PreparedStatement ps = con.prepareStatement(builder.toString())) {
			ps.setString(1, "%" + (bean.getName() == null ? "" : bean.getName()) + "%");
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong(DBFields.COUNT);
				}
				throw new SQLException(DBErrorMessages.CANT_GET_PRODUCTS_FROM_DB);
			}
		} catch (SQLException e) {
			throw new SQLException(DBErrorMessages.CANT_GET_PRODUCTS_FROM_DB, e);
		} catch (Exception e) {
			throw new SQLException(DBErrorMessages.CANT_PARSE_PRODUCTS_FROM_DB, e);
		}
	}
}
