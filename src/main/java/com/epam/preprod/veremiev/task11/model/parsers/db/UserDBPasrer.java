package com.epam.preprod.veremiev.task11.model.parsers.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.constants.DBFields;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.model.entities.utils.Role;

/**
 * Database result set parser
 * 
 * @author Illia_Veremiev
 *
 */
public class UserDBPasrer extends AbstractDBParser<User> {

	@Override
	public User parse(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(DBFields.USERS_ID));
		user.setName(rs.getString(DBFields.USERS_NAME));
		user.setSurname(rs.getString(DBFields.USERS_SURNAME));
		user.setLogin(rs.getString(DBFields.USERS_LOGIN));
		user.setEmail(rs.getString(DBFields.USERS_EMAIL));
		user.setPassword(rs.getString(DBFields.USERS_PASSWORD));
		user.setCountry(rs.getString(DBFields.USERS_COUNTRY));
		user.setPhoto(rs.getString(DBFields.USERS_PHOTO));
		user.setRole(Role.forValue(rs.getString(DBFields.USERS_ROLE)));
		return user;
	}
}
