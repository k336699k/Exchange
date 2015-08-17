package org.dao.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.database.dbutils.DBUtils;
import org.dao.database.pool.ConnectionPool;
import org.entity.User;
import org.resource.SqlManager;

import exception.DAOException;

public class UserDao extends AbstractDAO<User> implements UserDaoInterface {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	
	private static volatile UserDao instance;

	private UserDao() {
	}

	public synchronized static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	@Override
	public void addSubstance(User user) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_INSERT_USER"));
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

	@Override
	public User findSubstance(String login) {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_GET_ONE_USER"));
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			user = initSubstance(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, resultSet, connection);
		}
		return user;
	}

	@Override
	public User initSubstance(ResultSet resultSet) {
		User user = new User();
		try {
			while (resultSet.next()) {
				user.setiD(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setLogin(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));

			}
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		}
		return user;
	}

	@Override
	public List<User> readSubstances() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		List<User> users = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SqlManager.getProperty("SQL_GET_USERS"));
			users = (List<User>) initSubstances(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return users;
	}

	@Override
	public List<User> initSubstances(ResultSet resultSet) {
		List<User> users = new ArrayList<User>();
		try {
			while (resultSet.next()) {
				User user = new User();
				user.setiD(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setLogin(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				users.add(user);
			}
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		}
		return users;
	}

	@Override
	public void removeSubstance(String login) {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_DELETE_ONE_USER"));
			preparedStatement.setString(1, login);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

	@Override
	public User getUser(String login, String password) {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_CHECK_LOGIN"));
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			user = initSubstance(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, resultSet, connection);
		}
		return user;
	}

}
