package org.dao;

import org.dao.pojo.MetalPojo;
import org.dao.pojo.UserPojo;

import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.junit.Test;

import config.HibernateDbUnitTestCase;

public class MetalsDbUnitTest extends HibernateDbUnitTestCase {
	
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder()
				.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("metal-data.xml"));
	}

	@Test
	public void testGetMetalList() {
		Transaction trans = session.beginTransaction();
		assertEquals(2, session.createCriteria(MetalPojo.class).list().size());
		trans.commit();
	}

	@Test
	public void testGerMetalByTitle() {
		String title = "Арматура 50";
		MetalPojo metalPojo = new MetalPojo();
		session.beginTransaction();
		Query query = session.createQuery("from MetalPojo where title =:metalTitle").setString("metalTitle", title);
		metalPojo = (MetalPojo) query.uniqueResult();
		session.getTransaction().commit();
		assertEquals("Арматура 50", metalPojo.getTitle());

	}

	@Test
	public void testAddMetal() {
		MetalPojo metalPojo = new MetalPojo("Арматура 34", "2 т", 3000);
		session.beginTransaction();
		session.save(metalPojo);
		session.getTransaction().commit();

	}

	@Test
	public void testDeleteUser() {
		MetalPojo metalPojo = new MetalPojo("Арматура 34", "2 т", 3000);
		session.beginTransaction();
		session.delete(metalPojo);
		session.getTransaction().commit();

	}

}