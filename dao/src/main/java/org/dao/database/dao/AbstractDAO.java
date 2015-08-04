package org.dao.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.dao.database.dbutils.DBUtils;
import org.dao.database.pool.ConnectionPool;

public abstract class AbstractDAO<T> implements GenericDao<T> {

	@Override
	public void addSubstance(T object) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement("SQL_INSERT_NEWS");
			preparedStatement.setString(1, "T");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

	@Override
	public T findSubstance(String name) {
		T t = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement("SQL_GET_ONE_ROLE");
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			t = initSubstance(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, resultSet, connection);
		}
		return t;
	}

	T initSubstance(ResultSet resultSet) {
		return null;
	}

	public List<T> readSubstances() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		List<T> t = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SQL_GET_ROLES");
			t = (List<T>) initSubstances(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return t;
	}

	List<T> initSubstances(ResultSet resultSet) {
		return null;
	}

	@Override
	public void removeSubstance(String name) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement("SQL_DELETE_ONE_ROLE");
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

}
