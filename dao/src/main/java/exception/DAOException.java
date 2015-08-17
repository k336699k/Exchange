package exception;

import java.sql.SQLException;

public class DAOException extends SQLException {
	
	public DAOException() {

	}

	public DAOException(String message, Throwable arg) {
		super(message, arg);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable arg) {
		super(arg);
	}

}
