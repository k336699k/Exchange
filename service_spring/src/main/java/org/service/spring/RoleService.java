package org.service.spring;

import java.util.List;

import org.entity.Role;

public interface RoleService {
	
	Role getRole(String name);
	
	void deleteRole(String name);
	
	boolean addRole(String name);

	List<Role> getAllRoles();

	List<Role> getRoleByUser (String login);

}
