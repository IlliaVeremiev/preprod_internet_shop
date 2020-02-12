package com.epam.preprod.veremiev.task11.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.javalite.activejdbc.DBException;

import com.epam.preprod.veremiev.task11.constants.DBErrorMessages;
import com.epam.preprod.veremiev.task11.constants.MYSQLRequests;
import com.epam.preprod.veremiev.task11.database.dao.UserDAO;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.model.parsers.db.UserDBPasrer;

public class UserMYSQL implements UserDAO {

	@Override
	public boolean create(Connection con, User u) throws DBException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.INSERT_USER)) {
			int k = 1;
			ps.setString(k++, u.getName());
			ps.setString(k++, u.getSurname());
			ps.setString(k++, u.getLogin());
			ps.setString(k++, u.getEmail());
			ps.setString(k++, u.getPassword());
			ps.setString(k++, u.getCountry());
			ps.setString(k++, u.getPhoto());
			ps.setString(k, u.getRole().toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DBException(DBErrorMessages.CANT_ADD_USER_TO_DB, e);
		}
	}

	@Override
	public boolean update(Connection con, User u) throws DBException {
		return false;
	}

	@Override
	public User get(Connection con, long id) throws DBException {
		return null;
	}

	@Override
	public boolean remove(Connection con, User u) throws DBException {
		return false;
	}

	@Override
	public User getByLogin(Connection con, String login) throws DBException {
		try (PreparedStatement ps = con.prepareStatement(MYSQLRequests.GET_USER_BY_LOGIN)) {
			ps.setString(1, login);
			User user = null;
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					user = new UserDBPasrer().parse(rs);
				}
			}
			return user;
		} catch (SQLException e) {
			throw new DBException(DBErrorMessages.CANT_GET_USER_FROM_DB, e);
		}
	}
}
