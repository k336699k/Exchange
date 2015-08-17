package org.dao.database.dbutils;

import java.sql.*;

import org.apache.log4j.Logger;
import org.dao.database.dao.MetalDao;
import org.dao.database.pool.ConnectionPool;

import exception.DAOException;

public class DBUtils {
	private static final Logger LOGGER = Logger.getLogger(DBUtils.class);
	
	
	public static void close(Statement preparedStatement, Connection connection) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		}
	}

	public static void close(Statement preparedStatement, ResultSet resultSet, Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			 new DAOException(e);
			 LOGGER.error("DAOException", e);
		}
	}
}