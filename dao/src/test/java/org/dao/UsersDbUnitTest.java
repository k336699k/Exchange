package org.dao;

import org.dao.pojo.UserPojo;

import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.junit.Test;

import config.HibernateDbUnitTestCase;

public class UsersDbUnitTest extends HibernateDbUnitTestCase {

	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("user-data.xml"));
	}

	
	@Test
	public void testGetUserList() {
		Transaction trans = session.beginTransaction();
		assertEquals(2, session.createCriteria(UserPojo.class).list().size());
		trans.commit();
	}

	
	@Test
	public void testGerUserByLogin() {
	    String login = "mail@tut.by";
	    UserPojo userPojo = new UserPojo();
		session.beginTransaction();
		Query query = session.createQuery("from UserPojo where login =:userLogin")
				.setString("userLogin", login);
		userPojo = (UserPojo) query.uniqueResult();
		session.getTransaction().commit();
		assertEquals("Oleg", userPojo.getFirstName());
		
		
	}

	@Test
	public void testAddUser() {
		UserPojo userPojo = new UserPojo("Никита", "Батюшков", "nikita@tut.by", "43498");
		session.beginTransaction();
		session.save(userPojo);
		session.getTransaction().commit();
		
	}
	@Test
	public void testDeleteUser() {
		UserPojo userPojo = new UserPojo("Никита", "Батюшков", "nikita@tut.by", "43498");
		session.beginTransaction();
		session.delete(userPojo);
		session.getTransaction().commit();
		
	}
	
	
}