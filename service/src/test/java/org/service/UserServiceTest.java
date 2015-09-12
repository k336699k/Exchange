package org.service;

import static org.junit.Assert.*;

import org.dao.UserDao;
import org.dao.iterface.GenericDao;
import org.dao.iterface.UserDaoInterface;
import org.entity.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class UserServiceTest {
	
	private Mockery context = new JUnit4Mockery();
	final GenericDao <User> userGenericDAO= context.mock(GenericDao.class);
	final UserDaoInterface userDAO= context.mock(UserDaoInterface.class);

	@Test
	public void testGetUser() {
	//	final UserDaoInterface userDao= context.mock(UserDaoInterface.class);
		final String login = "k336699k@mail.ru";
		final String password = "123456";
		final User user = new User("Константин", "Шаплыко", "k336699k@mail.ru", "123456");
		context.checking(new Expectations() {
			{
				oneOf(userDAO).getUser(login, password);
				will(returnValue(user));
			}
		});
		UserService userService = new UserService ();
		User userNew = new User();
		userService.setUserDao(userDAO);
		userNew  = userService.getUser(login, password);
	//	assertEquals(user,userNew);
	//	assertEquals(user.getPassword(),userNew.getPassword());
	}
	
	@Test
	public void testAddUser() {
	//	final GenericDao <User> userGenericDAO= context.mock(GenericDao.class);
		final User user = new User("Иван", "Иванов", "kk@mail.ru", "1456");
		context.checking(new Expectations() {
			{
				oneOf(userDAO).addSubstance(user);
				
			}
			});
	   	    UserService userService = new UserService ();
	   	    userService.setUserDao(userDAO);
	      	 userService.addUser("Иван", "Иванов", "kk@mail.ru", "1456");
	   
	  	}
	
	@Test
	public void testDeleteUser() {
	//	final GenericDao <User> userGenericDAO= context.mock(GenericDao.class);
		final User user = new User("Константин", "Шаплыко", "k336699k@mail.ru", "123456");
		final String login = "k336699k@mail.ru";
		context.checking(new Expectations() {
			{
				oneOf(userGenericDAO).removeSubstance(login);
						
			}
			});
	   	    UserService userService = new UserService ();
	   	    userService.setUserGenericDao(userGenericDAO);
	   	    userService.deleteUser(login);
	  	}
	
}