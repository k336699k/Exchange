package org.dao.database.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.database.dbutils.DBUtils;
import org.dao.database.pool.ConnectionPool;
import org.entity.Role;
import org.resource.SqlManager;

import exception.DAOException;

public class RoleDao extends AbstractDAO<Role> {
	private static final Logger LOGGER = Logger.getLogger(RoleDao.class);
	
	private static volatile RoleDao instance;

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
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_INSERT_ROLE"));
			preparedStatement.setString(1, role.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
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
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_GET_ONE_ROLE"));
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			role = initSubstance(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
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
			new DAOException(e);
			LOGGER.error("DAOException", e);
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
			resultSet = statement.executeQuery(SqlManager.getProperty("SQL_GET_ROLES"));
			roles = (List<Role>) initSubstances(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
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
			new DAOException(e);
			LOGGER.error("DAOException", e);
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
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_DELETE_ONE_ROLE"));
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

}
