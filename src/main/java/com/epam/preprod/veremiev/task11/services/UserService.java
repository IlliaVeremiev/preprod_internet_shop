package com.epam.preprod.veremiev.task11.services;

import java.sql.SQLException;

import com.epam.preprod.veremiev.task11.constants.ExceptionMessages;
import com.epam.preprod.veremiev.task11.database.dao.UserDAO;
import com.epam.preprod.veremiev.task11.database.mysql.TransactionManager;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.bean.UserDataBean;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.model.parsers.bean.UserBeanParser;

/**
 * Makes some logic with users
 * 
 * @author Illia_Veremiev
 *
 */
public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Registrates new user
	 * 
	 * @param userBean - user input bean
	 * @return registrated user
	 * @throws ServiceException if errors occurs
	 */
	public User registrate(UserDataBean userBean) throws ServiceException {
		User user = new UserBeanParser().parse(userBean);
		try {
			new TransactionManager().doInTransaction(con -> {
				if (!userDAO.create(con, user)) {
					throw new SQLException(ExceptionMessages.CANT_ADD_USER_TO_DATABASE);
				}
				return true;
			});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionMessages.CANT_REGISTRATE_USER);
		}
		return user;
	}

	/**
	 * Login user
	 * 
	 * @param userBean - user input bean
	 * @return login user
	 * @throws ServiceException if errors occurs
	 */
	public User login(UserDataBean userBean) throws ServiceException {
		User userFromDb;
		try {
			userFromDb = new TransactionManager().doInTransaction(con -> userDAO.getByLogin(con, userBean.getLogin()));
		} catch (SQLException e) {
			throw new ServiceException(ExceptionMessages.CANT_LOGIN_USER);
		}
		if (userFromDb == null) {
			throw new ServiceException(ExceptionMessages.NO_USER_WITH_SUCH_LOGIN_PASSWORD);
		}
		if (!userFromDb.getPassword().equals(userBean.getPassword())) {
			throw new ServiceException(ExceptionMessages.NO_USER_WITH_SUCH_LOGIN_PASSWORD);
		}
		return userFromDb;
	}
}
