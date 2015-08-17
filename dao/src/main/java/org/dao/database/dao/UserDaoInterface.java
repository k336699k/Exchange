package org.dao.database.dao;

import org.entity.User;

public interface UserDaoInterface {
	 User getUser(String login, String password);

}
