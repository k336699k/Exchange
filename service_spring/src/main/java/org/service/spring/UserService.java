package org.service.spring;




import java.util.List;


import org.entity.User;



public interface UserService {

	User getUser(String login, String password);
	
	User getUserByLogin (String login);

	boolean checkLogin(String enterLogin, String enterPass) throws ClassNotFoundException;

	void deleteUser(String login);

	boolean addUser(String firstName, String lastName, String login, String password);
	
	List<User> getAllUsers();
	
	List<User> getUserByRole (String roleName);
	

}
