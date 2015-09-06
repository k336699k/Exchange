package org.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.convert.ConvetrToClass;
import org.dao.iterface.GenericDao;
import org.dao.iterface.RoleDaoInterface;
import org.dao.pojo.RolePojo;
import org.dao.util.HibernateUtil;
import org.entity.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.resource.SqlManager;

import exception.DAOException;

public class RoleDao implements RoleDaoInterface, GenericDao<Role> {
	private static final Logger LOGGER = Logger.getLogger(RoleDao.class);
	
	private static volatile RoleDao instance;

	private RoleDao() {
	}

	public synchronized static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}

	
	
	public void addSubstance(Role role) {
		RolePojo rolelPojo = new RolePojo();
		rolelPojo = ConvetrToClass.convetrToRolePojo(role);
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(rolelPojo);
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

	public Role findSubstance(String name) {
		Session session = null;
		RolePojo rolelPojo = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from RolePojo where name =:roleName").setString("roleName", name);
			rolelPojo = (RolePojo) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ConvetrToClass.convetrToRole(rolelPojo);
	}

	public List<Role> readSubstances() {
		Session session = null;
		List rolesPojo = new ArrayList<RolePojo>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			rolesPojo = session.createCriteria(RolePojo.class).list();
		} catch (Exception e) {
			new DAOException(e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ConvetrToClass.convetrToRoleCollection(rolesPojo);
	}

	public void removeSubstance(String name) {
		Session session = null;
		RolePojo rolePojo = new RolePojo();
		rolePojo = ConvetrToClass.convetrToRolePojo(findSubstance(name));
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(rolePojo);
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
	
	public Collection getRoleByUser(String login) {
		Session session = null;
		List rolesPojo = new ArrayList<RolePojo>();
		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session
					.createQuery(
							" select b " + " from RolePojo b INNER JOIN b.users user" + " where user.login = :userLogin ")
					.setString("userLogin", login);
			rolesPojo = (List<RolePojo>) query.list();
			session.getTransaction().commit();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ConvetrToClass.convetrToRoleCollection(rolesPojo);
	}
}
	
	
	
	

