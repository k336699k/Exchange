package org.service;

import java.util.List;

import org.entity.Role;

public interface RoleServiceInterface {

	void addRole(String name);

	Role findRole(String name);

	List<Role> readRole();

	void removeRole(String name);

}
