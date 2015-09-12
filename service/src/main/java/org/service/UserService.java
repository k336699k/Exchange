package org.service;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dao.UserDao;
import org.dao.iterface.GenericDao;
import org.dao.iterface.UserDaoInterface;
import org.entity.User;
import org.exception.ServiceException;

public class UserService implements UserServiceInterface {
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	private GenericDao<User> userGenericDao = UserDao.getInstance();
	private UserDaoInterface userDao = UserDao.getInstance();

	
	public GenericDao<User> getUserGenericDao() {
		if (userGenericDao == null) {
			LOGGER.error("I could not create UserDAO across GenericDao. UserDAO in null.");
			new ServiceException("I could not create UserDAO across GenericDao. UserDAO in null.");
		}
		return userGenericDao;
	}

	public void setUserGenericDao(GenericDao<User> userGenericDao) {
		this.userGenericDao = userGenericDao;
	}

	public UserDaoInterface getUserDao() {
		if (userDao == null) {
			LOGGER.error("I could not create UserDAO across UserDaoInterface. UserDAO in null.");
			new ServiceException("I could not create UserDAO across UserDaoInterface. UserDAO in null.");
		}
		return userDao;
	}

	public void setUserDao(UserDaoInterface userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(String login, String password) {
		LOGGER.info("User received, its login: " + login);
		return getUserDao().getUser(login, password);
	}

	@Override
	public boolean checkLogin(String enterLogin, String enterPass) throws ClassNotFoundException {
		boolean count = false;
		User user = null;
		user = getUser(enterLogin, enterPass);
		if (user.getLogin() != null) {
			count = true;
		}
		LOGGER.info("User login is checked: " + count);
		return count;
	}

	@Override
	public void deleteUser(String login) {
		getUserGenericDao().removeSubstance(login);
	LOGGER.info("launched method deleteUser() in UserService");
	}

	@Override
	public boolean addUser(String firstName, String lastName, String login, String password) {
		User user = new User(firstName, lastName, login, password);
	//	getUserDao().addSubstance(user);
		LOGGER.info("launched method addUser() in UserService");
		return getUserDao().addSubstance(user);
	}
	
	private static String getEncryptUser(String message) {
		String md5Hex = DigestUtils.md5(message).toString();
		
		return md5Hex;
	}
}
