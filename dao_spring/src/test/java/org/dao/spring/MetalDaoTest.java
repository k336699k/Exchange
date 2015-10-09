
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
import org.dao.spring.pojo.MetalPojo;
import org.dao.spring.pojo.RolePojo;
import org.dao.spring.repository.MetalDaoRepository;
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
public class MetalDaoTest {
	MetalDaoRepository metalDaoRepository;
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
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("metal-data.xml"));
		try {
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataset);
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}

		metalDaoRepository = (MetalDaoRepository) context.getBean("metalDaoRepository");

	}

	@Test
	public void testReadAll() throws Exception {

		System.out.println(metalDaoRepository.findAll());
		assertEquals(2, metalDaoRepository.findAll().size());
	}

	@Test
	public void testAddRole() throws Exception {
		MetalPojo metal = new MetalPojo();
		metal.setTitle("Профильная труба 30");
		metal.setQuantity("35 т");
		metal.setPrice(3400);
		
		assertNotNull(metalDaoRepository.saveAndFlush(metal));
	}

	@Test
	public void testFindByName() throws Exception {

		assertNotNull(metalDaoRepository.findByName("Арматура 50"));

	}

	@Test
	public void testDelete() throws Exception {
		metalDaoRepository.delete(1);
		assertNull(metalDaoRepository.findOne(1));

	}

}
