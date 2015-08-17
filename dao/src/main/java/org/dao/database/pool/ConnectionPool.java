package org.dao.database.pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.Logger;

import exception.DAOException;

import static org.resource.Constant.*;

public class ConnectionPool {
	
	private static ConnectionPool instance;
	private BasicDataSource dataSource = new BasicDataSource();

	private ConnectionPool() {
		this.dataSource.setDriverClassName(SQL_DRIVER);
		this.dataSource.setUrl(SQL_LOCALHOST);
		this.dataSource.setUsername(DB_LOGIN);
		this.dataSource.setPassword(DB_PASSWORD);
	}

	public static synchronized ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}

		return instance;
	}

	public Connection getConnection() throws DAOException  {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void closeConnection() throws DAOException{
		try {
			this.dataSource.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
