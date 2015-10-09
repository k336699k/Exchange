package org.dao.spring;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.dao.spring.pojo.UserPojo;
import org.dao.spring.repository.UserDaoRepository;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class UserDaoTest {
	UserDaoRepository userDaoRepository;
	protected static EntityManagerFactory factory;
	protected static EntityManager em;
	private static IDataSet dataset;
	private static IDatabaseConnection connection;

	@Before
	public void loadConfig() throws HibernateException, DatabaseUnitException, SQLException {
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(TestDataBaseConfig.class);
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		dataset = flatXmlDataSetBuilder
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("user-data.xml"));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataset);
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}

		userDaoRepository = (UserDaoRepository) context.getBean("userDaoRepository");

	}

	@Test
	public void testReadAll() throws Exception {

		System.out.println(userDaoRepository.findAll());
		assertEquals(2, userDaoRepository.findAll().size());
	}

	@Test
	public void testAddUser() throws Exception {
		UserPojo user = new UserPojo();
		user.setLogin("tester");
		user.setFirstName("tester");
		user.setPassword("123");
		user.setLastName("tester");
		assertNotNull(userDaoRepository.saveAndFlush(user));
	}

	@Test
	public void testFindByLogin() throws Exception {

		assertNotNull(userDaoRepository.findByLogin("mail@tut.by"));

	}

	@Test
	public void testFindByLoginAndPassword() throws Exception {

		assertNotNull(userDaoRepository.findByLoginAndPassword("mail@tut.by", "123"));

	}

	@Test
	public void testDelete() throws Exception {
		userDaoRepository.delete(1);
		assertNull(userDaoRepository.findOne(1));

	}

	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Test public void testDelete() throws Exception {
	 * userDaoRepository.delete(3);
	 * Assert.assertNull(userDaoRepository.findOne(3));
	 * System.out.println(userDaoRepository.findAll()); }
	 * 
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Test public void testUpdate() throws Exception { UserPojo user = new
	 * UserPojo(); user.setiD(1); user.setLogin("tester");
	 * user.setFirstName("tester"); user.setPassword("123");
	 * user.setLastName("tester");
	 * Assert.assertNotNull(userDaoRepository.saveAndFlush(user));
	 * System.out.println(userDaoRepository.findAll()); }
	 */

}
