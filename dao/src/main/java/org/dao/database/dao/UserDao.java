package org.dao.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dao.database.dbutils.DBUtils;
import org.dao.database.pool.ConnectionPool;
import org.entity.User;

public class UserDao extends AbstractDAO<User> {
	public static final String SQL_INSERT_USER = "INSERT INTO users (first_name, last_name, login, password) VALUES (?,?,?,?)";
	public static final String SQL_GET_USERS = "SELECT * FROM users";
	public static final String SQL_GET_ONE_USER = "SELECT * FROM users WHERE login=?";
	public static final String SQL_DELETE_ONE_USER = "DELETE FROM users WHERE login=?";
	private static UserDao instance;

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
			preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
			preparedStatement = connection.prepareStatement(SQL_GET_ONE_USER);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			user = initSubstance(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
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

			e.printStackTrace();
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
			resultSet = statement.executeQuery(SQL_GET_USERS);
			users = (List<User>) initSubstances(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
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

			e.printStackTrace();
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
			preparedStatement = connection.prepareStatement(SQL_DELETE_ONE_USER);
			preparedStatement.setString(1, login);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

}
