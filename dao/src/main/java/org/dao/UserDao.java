package org.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dao.convert.ConvetrToClass;
import org.dao.iterface.GenericDao;
import org.dao.iterface.UserDaoInterface;
import org.dao.pojo.RolePojo;
import org.dao.pojo.UserPojo;
import org.dao.util.HibernateUtil;
import org.entity.Role;
import org.entity.User;
import org.exception.TechnicalException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.resource.SqlManager;

import exception.DAOException;

public class UserDao implements UserDaoInterface, GenericDao<User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	private static volatile UserDao instance;

	private UserDao() {
	}

	public synchronized static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public boolean addSubstance(User user) {
		boolean flag = false;
		User userNew = new User();
		userNew = getUser (user.getLogin(), user.getPassword());
		if (userNew.getLogin() == null) {
		UserPojo userPojo = new UserPojo();
		userPojo = ConvetrToClass.convetrToUserPojo(user);
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(userPojo);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			new TechnicalException("Exception in addSubstance", e);
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

	public User findSubstance(String login) {
		Session session = null;
		UserPojo userPojo = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from UserPojo where login =:userLogin").setString("userLogin", login);
			// (SqlManager.getProperty("SQL_GET_ONE_USER")
			userPojo = (UserPojo) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			new TechnicalException("Exception in findSubstance", e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ConvetrToClass.convetrToUser(userPojo);
	}

	public List<User> readSubstances() {
		Session session = null;
		List usersPojo = new ArrayList<UserPojo>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usersPojo = session.createCriteria(UserPojo.class).list();
		} catch (Exception e) {
			new TechnicalException("Exception in readSubstances", e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return ConvetrToClass.convetrToUserCollection(usersPojo);
	}

	public void removeSubstance(String login) {
		Session session = null;
		UserPojo userPojo = new UserPojo();
		userPojo = ConvetrToClass.convetrToUserPojo(findSubstance(login));
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(userPojo);
			session.getTransaction().commit();
		} catch (Exception e) {
			new TechnicalException("Exception in removeSubstance", e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public User getUser(String login, String password) {
		Session session = null;
		UserPojo userPojo = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from UserPojo where login =:userLogin and password =:userPassword")
					.setString("userLogin", login).setString("userPassword", password);
			userPojo = (UserPojo) query.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			new TechnicalException("Exception in getUser", e);
			LOGGER.error("DAOException", e);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ConvetrToClass.convetrToUser(userPojo);
	}

	public void updateSubstance(String login, String role){
		Session session = null;
		Set<RolePojo> rolesPojo = new HashSet<>();
		UserPojo userPojo = new UserPojo();
		userPojo = ConvetrToClass.convetrToUserPojo(findSubstance(login));
		GenericDao<Role> roleDao = RoleDao.getInstance();
		roleDao.findSubstance(role);
		rolesPojo.add(ConvetrToClass.convetrToRolePojo(roleDao.findSubstance(role)));
		userPojo.setRoles(rolesPojo);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(userPojo);
			session.getTransaction().commit();
		} catch (Exception e) {
			new TechnicalException("Exception in updateSubstance", e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public Collection getUserByRole(String role) {
		Session session = null;
		List usersPojo = new ArrayList<UserPojo>();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			Query query = session
					.createQuery(
							" select b " + " from UserPojo b INNER JOIN b.roles role" + " where role.name = :roleName ")
					.setString("roleName", role);
			usersPojo = (List<UserPojo>) query.list();
			session.getTransaction().commit();

		} catch (Exception e) {
			new TechnicalException("Exception in getUserByRole", e);
			LOGGER.error("DAOException", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();

			}

		}
		return ConvetrToClass.convetrToUserCollection(usersPojo);
	}
}
