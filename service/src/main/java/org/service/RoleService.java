package org.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.MetalDao;
import org.dao.RoleDao;
import org.dao.iterface.GenericDao;
import org.dao.iterface.MetalDaoInterface;
import org.dao.iterface.RoleDaoInterface;
import org.entity.Metal;
import org.entity.Role;
import org.exception.ServiceException;

public class RoleService implements RoleServiceInterface {
	private GenericDao<Role> roleGenericDao = null;
	private RoleDaoInterface roleDao = null;
	private static final Logger LOGGER = Logger.getLogger(RoleService.class);

	public RoleService() {
		roleGenericDao = RoleDao.getInstance();
		roleDao = RoleDao.getInstance();
	}

	
	
	public RoleDaoInterface getRoleDao() {
		if (roleDao == null) {
			LOGGER.error("I could not create RoleDAO. RoleDAO in null.");
			new ServiceException("I could not create RoleDAO. RoleDAO in null.");
		}
		return roleDao;
	}



	public void setRoleDao(RoleDaoInterface roleDao) {
		this.roleDao = roleDao;
	}



	public GenericDao<Role> getRoleGenericDao() {
		if (roleGenericDao == null) {
			LOGGER.error("I could not create RoleDAO. RoleDAO in null.");
			new ServiceException("I could not create RoleDAO. RoleDAO in null.");
		}
		return roleGenericDao;
	}

	public void setRoleGenericDao(GenericDao<Role> roleGenericDao) {
		this.roleGenericDao = roleGenericDao;
	}

	@Override
	public void addRole(String name) {
		Role role = new Role(name);
		getRoleDao().addSubstance(role);
		LOGGER.info("Role added, its name: " + name);

	}

	@Override
	public Role findRole(String name) {
		LOGGER.info("Role found, its name: " + name);
		return getRoleGenericDao().findSubstance(name);
	}

	@Override
	public List<Role> readRole() {
		ArrayList<Role> roles = (ArrayList<Role>) getRoleGenericDao().readSubstances();
		LOGGER.info("launched method readRole() in RoleService");
		return roles;
	}

	@Override
	public void removeRole(String name) {
		getRoleGenericDao().removeSubstance(name);
		LOGGER.info("launched method removeRole() in RoleService");
	}

}
