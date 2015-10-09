
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
import org.dao.spring.pojo.RolePojo;

import org.dao.spring.repository.RoleDaoRepository;

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
public class RoleDaoTest {
	RoleDaoRepository roleDaoRepository;
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
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("role-data.xml"));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataset);
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}

		roleDaoRepository = (RoleDaoRepository) context.getBean("roleDaoRepository");

	}

	@Test
	public void testReadAll() throws Exception {

		System.out.println(roleDaoRepository.findAll());
		assertEquals(2, roleDaoRepository.findAll().size());
	}

	@Test
	public void testAddRole() throws Exception {
		RolePojo role = new RolePojo();
		role.setName("Автолюбитель");

		assertNotNull(roleDaoRepository.saveAndFlush(role));
	}

	@Test
	public void testFindByName() throws Exception {

		assertNotNull(roleDaoRepository.findByName("Пользователь"));

	}

	@Test
	public void testDelete() throws Exception {
		roleDaoRepository.delete(1);
		assertNull(roleDaoRepository.findOne(1));

	}

}
