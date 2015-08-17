package org.dao.database.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.database.dbutils.DBUtils;
import org.dao.database.pool.ConnectionPool;
import org.entity.Metal;
import org.resource.SqlManager;

import exception.DAOException;

public class MetalDao extends AbstractDAO<Metal> {
	private static final Logger LOGGER = Logger.getLogger(MetalDao.class);
	
	private static volatile MetalDao instance;

	private MetalDao() {
	}

	public synchronized static MetalDao getInstance() {
		if (instance == null) {
			instance = new MetalDao();
		}
		return instance;
	}

	@Override
	public void addSubstance(Metal metal) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_INSERT_METAL"));
			preparedStatement.setString(1, metal.getTitle());
			preparedStatement.setString(2, metal.getQuantity());
			preparedStatement.setInt(3, metal.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

	@Override
	public Metal findSubstance(String title) {
		Metal metal = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_GET_ONE_METAL"));
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();
			metal = initSubstance(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, resultSet, connection);
		}
		return metal;
	}

	@Override
	public Metal initSubstance(ResultSet resultSet) {
		Metal metal = new Metal();
		try {
			while (resultSet.next()) {
				metal.setiD(resultSet.getInt(1));
				metal.setTitle(resultSet.getString(2));
				metal.setQuantity(resultSet.getString(3));
				metal.setPrice(resultSet.getInt(4));

			}
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		}
		return metal;
	}

	@Override
	public List<Metal> readSubstances() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		List<Metal> metals = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SqlManager.getProperty("SQL_GET_METALS"));
			metals = (List<Metal>) initSubstances(resultSet);
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return metals;
	}

	@Override
	public List<Metal> initSubstances(ResultSet resultSet) {
		List<Metal> metals = new ArrayList<Metal>();
		try {
			while (resultSet.next()) {
				Metal metal = new Metal();
				metal.setiD(resultSet.getInt(1));
				metal.setTitle(resultSet.getString(2));
				metal.setQuantity(resultSet.getString(3));
				metal.setPrice(resultSet.getInt(4));
				metals.add(metal);
			}
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		}
		return metals;
	}

	@Override
	public void removeSubstance(String title) {
		Metal metal = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SqlManager.getProperty("SQL_DELETE_ONE_METAL"));
			preparedStatement.setString(1, title);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			DBUtils.close(preparedStatement, connection);
		}
	}

}
