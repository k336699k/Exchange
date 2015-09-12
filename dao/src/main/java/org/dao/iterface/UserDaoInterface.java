package org.dao.iterface;


import java.sql.SQLException;
import java.util.Collection;


import org.entity.User;

public interface UserDaoInterface {
	 User getUser(String login, String password);
	 Collection getUserByRole(String role);
	 void updateSubstance(String login, String role);
	 boolean addSubstance(User user);
}
