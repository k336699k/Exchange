package org.service;

import org.entity.User;

public interface UserServiceInterface {

	User getUser(String login, String password);

	boolean checkLogin(String enterLogin, String enterPass) throws ClassNotFoundException;

	void deleteUser(String login);

	void addUser(String firstName, String lastName, String login, String password);

}
