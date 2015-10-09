package org.service.spring;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dao.spring.pojo.UserPojo;
import org.dao.spring.repository.UserDaoRepository;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class UserServiceTest {
	
	private Mockery context = new JUnit4Mockery();
	
	private UserDaoRepository userDaoRepository = context.mock(UserDaoRepository.class);

	@Test
	public void testGetUser() {
	
		final String login = "k336699k@mail.ru";
		final String password = "123456";
		final UserPojo user = new UserPojo("Константин", "Шаплыко", "k336699k@mail.ru", "123456");
		context.checking(new Expectations() {
			{
				oneOf(userDaoRepository).findByLoginAndPassword(login, password);
				will(returnValue(user));
			}
		});
		UserServiceImpl userService = new UserServiceImpl ();
		userService.setUserDaoRepository(userDaoRepository);
		assertNotNull(userService.getUser(login, password));
		
	}
	
	@Test
	public void testGetUserByLogin() {
		final String login = "k336699k@mail.ru";
		final UserPojo user = new UserPojo("Константин", "Шаплыко", "k336699k@mail.ru", "123456");
		context.checking(new Expectations() {
			{
				oneOf(userDaoRepository).findByLogin(login);
				will(returnValue(user));
			}
		});
		UserServiceImpl userService = new UserServiceImpl ();
		userService.setUserDaoRepository(userDaoRepository);
		assertNotNull(userService.getUserByLogin(login));
	}
	
	@Test
	public void testGetAllUsers() {
		final List users = new ArrayList<UserPojo>();
		users.add(new UserPojo("Константин", "Шаплыко", "k336699k@mail.ru", "123456"));
		users.add(new UserPojo("Никита", "Просит", "k336699k@mail.ru", "123456"));
		context.checking(new Expectations() {
			{
				oneOf(userDaoRepository).findAll();
				will(returnValue(users));
			}
		});
		UserServiceImpl userService = new UserServiceImpl ();
		userService.setUserDaoRepository(userDaoRepository);
	//	userService.getAllUsers();
		assertEquals(2, userService.getAllUsers().size());
	}
	
	/*
	@Test
	public void testAddUser() {
	
		final UserPojo user = new UserPojo("Иван", "Иванов", "kk@mail.ru", "1456");
		context.checking(new Expectations() {
			{
				oneOf(userDaoRepository).saveAndFlush(user);
			//	will(returnValue(true));
			}
			});
		    UserServiceImpl userService = new UserServiceImpl ();
	   	    userService.setUserDaoRepository(userDaoRepository);
	     // 	 userService.addUser("Иван", "Иванов", "kk@mail.ru", "1456");
	      	assertEquals(true, userService.addUser("Иван", "Иванов", "kk@mail.ru", "1456"));
	  	}
	
	@Test
	public void testDeleteUser() {
	
		final UserPojo user = new UserPojo (1,"Константин", "Шаплыко", "k336699k@mail.ru", "123456");
		final String login = "k336699k@mail.ru";
		context.checking(new Expectations() {
			{
				oneOf(userDaoRepository).delete(user.getiD());
						
			}
			});
		    UserServiceImpl userService = new UserServiceImpl ();
   	        userService.setUserDaoRepository(userDaoRepository);
	   	    userService.deleteUser(login);
	   	    
	  	}
	*/
}