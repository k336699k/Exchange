package org.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.convert.ConvetrToClass;
import org.dao.iterface.GenericDao;
import org.dao.iterface.MetalDaoInterface;
import org.dao.pojo.MetalPojo;
import org.dao.util.HibernateUtil;
import org.entity.Metal;
import org.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.resource.SqlManager;

import exception.DAOException;

public class MetalDao implements MetalDaoInterface, GenericDao<Metal> {
	private static final Logger LOGGER = Logger.getLogger(MetalDao.class);
	
	private static volatile MetalDao instance;

	private MetalDao() {
	}

	public synchronized static MetalDao getInstance() {
		if (instance == null) {
			instance = new MetalDao();
		}
		return instance;
	}

	
	
	public boolean addSubstance(Metal metal) {
		boolean flag = false;
		Metal metalNew = new Metal();
		metalNew = findSubstance (metal.getTitle());
		if (metalNew.getTitle() == null){
		MetalPojo metalPojo = new MetalPojo();
		metalPojo = ConvetrToClass.convetrToMetalPojo(metal);
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(metalPojo);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
		} else {
			flag = false;
		}
		return flag;
	}

	public Metal findSubstance(String title) {
		Session session = null;
		MetalPojo metalPojo = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from MetalPojo where title =:metalTitle").setString("metalTitle", title);
			metalPojo = (MetalPojo) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ConvetrToClass.convetrToMetal(metalPojo);
	}

	public List<Metal> readSubstances() {
		Session session = null;
		List metalsPojo = new ArrayList<MetalPojo>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			metalsPojo = session.createCriteria(MetalPojo.class).list();
		} catch (Exception e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ConvetrToClass.convetrToMetalCollection(metalsPojo);
	}

	public void removeSubstance(String title) {
		Session session = null;
		MetalPojo metalPojo = new MetalPojo();
		metalPojo = ConvetrToClass.convetrToMetalPojo(findSubstance(title));
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(metalPojo);
			session.getTransaction().commit();
		} catch (Exception e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
