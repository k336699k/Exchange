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
import org.entity.Role;

public class RoleDao extends AbstractDAO<Role> {
	public static final String SQL_INSERT_ROLE = "INSERT INTO roles (name) VALUES (?)";
	public static final String SQL_GET_ROLES = "SELECT * FROM roles";
	public static final String SQL_GET_ONE_ROLE = "SELECT * FROM roles WHERE name=?";
	public static final String SQL_DELETE_ONE_ROLE = "DELETE FROM roles WHERE name=?";
	private static RoleDao instance;

	private RoleDao() {
	}

	public synchronized static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}

	@Override
	public void addSubstance(Role role) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE);
			preparedStatement.setString(1, role.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

	@Override
	public Role findSubstance(String name) {
		Role role = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_GET_ONE_ROLE);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			role = initSubstance(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, resultSet, connection);
		}
		return role;
	}

	@Override
	public Role initSubstance(ResultSet resultSet) {
		Role role = new Role();
		try {
			while (resultSet.next()) {
				role.setiD(resultSet.getInt(1));
				role.setName(resultSet.getString(2));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<Role> readSubstances() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		List<Role> roles = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_GET_ROLES);
			roles = (List<Role>) initSubstances(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return roles;
	}

	@Override
	public List<Role> initSubstances(ResultSet resultSet) {
		List<Role> roles = new ArrayList<Role>();
		try {
			while (resultSet.next()) {
				Role role = new Role();
				role.setiD(resultSet.getInt(1));
				role.setName(resultSet.getString(2));
				roles.add(role);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public void removeSubstance(String name) {
		Role role = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_ONE_ROLE);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

}
