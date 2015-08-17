package org.resource;

public class Constant {

	// PATH
	public static final String SQL_CONFIG = "sql";
	public static final String PAGE_CONFIG = "page";
	public static final String MESSAGES_CONFIG = "messages";

	// ConnectionPool
	public static final String SQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String SQL_LOCALHOST = "jdbc:mysql://localhost:3306/exchange";
	public static final String DB_LOGIN = "root";
	public static final String DB_PASSWORD = "admin123";

	// Controller
	public static final String COMMAND = "command";
	public static final String PARAM_COMMAND_IS_NULL = "Param command is null";
	// LiginCommand
	public static final String PARAM_NAME_LOGIN = "login";
	public static final String PARAM_NAME_PASSWORD = "password";
	// SaveMetalCommand
	public static final String PARAM_NAME_TITLE = "title";
	public static final String PARAM_NAME_QUANTITY = "quantity";
	public static final String PARAM_NAME_PRICE = "price";
	// SaveUserCommand
	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";
}
