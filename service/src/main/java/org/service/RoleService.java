package org.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.database.dao.GenericDao;
import org.dao.database.dao.MetalDao;
import org.dao.database.dao.RoleDao;
import org.entity.Metal;
import org.entity.Role;
import org.exception.ServiceException;

public class RoleService implements RoleServiceInterface {
	private GenericDao<Role> roleGenericDao = null;
	private static final Logger LOGGER = Logger.getLogger(RoleService.class);

	public RoleService() {
		roleGenericDao = RoleDao.getInstance();
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
		getRoleGenericDao().addSubstance(role);
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
