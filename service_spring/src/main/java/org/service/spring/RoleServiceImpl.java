package org.service.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.spring.pojo.RolePojo;

import org.dao.spring.repository.RoleDaoRepository;

import org.entity.Role;

import org.service.spring.convert.ConvetrToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {
	private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleDaoRepository roleDaoRepository;
	
		
	public RoleDaoRepository getRoleDaoRepository() {
		return roleDaoRepository;
	}

	public void setRoleDaoRepository(RoleDaoRepository roleDaoRepository) {
		this.roleDaoRepository = roleDaoRepository;
	}

	@Override
	public Role getRole(String name) {
		LOGGER.info("launched method getRole() in RoleService");
		return ConvetrToClass.convetrToRole(roleDaoRepository.findByName(name));
	}

	@Override
	public void deleteRole(String name) {
		Role role = null;
		role = getRole(name);
		if (role.getName() != null) {
			roleDaoRepository.delete(role.getiD());
		}
		 LOGGER.info("launched method deleteRole() in RoleService");
	}
		
	

	@Override
	public boolean addRole(String name) {
		boolean flag = false;
		Role role  = getRole (name);
		if (role.getName() == null) {
		Role newRole = new Role (name);
		roleDaoRepository.saveAndFlush(ConvetrToClass.convetrToRolePojo(newRole));
		flag = true;
		}
		
		 LOGGER.info("launched method addRole() in RoleService");
		
		return flag;
	}

	@Override
	public List<Role> getAllRoles() {
		LOGGER.info("launched method getAllRoles() in RoleService");
		return ConvetrToClass.convetrToRoleCollection(roleDaoRepository.findAll());
	}

	@Override
	public List<Role> getRoleByUser(String login) {
		LOGGER.info("launched method getRoleByUser() in RoleService");
		return ConvetrToClass.convetrToRoleCollection((List<RolePojo>) roleDaoRepository.getRoleByUser(login));
	}

}
