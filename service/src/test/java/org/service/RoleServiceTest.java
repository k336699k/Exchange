package org.service;

import java.util.ArrayList;
import java.util.List;

import org.dao.iterface.GenericDao;
import org.dao.iterface.RoleDaoInterface;
import org.entity.Role;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class RoleServiceTest {
	private Mockery context = new JUnit4Mockery();
	final GenericDao<Role> roleGenericDAO = context.mock(GenericDao.class);
	final RoleDaoInterface roleDAO = context.mock(RoleDaoInterface.class);
	
	@Test
	public void testReadRole() {

		final Role role1 = new Role("Администратор");
		final Role role2 = new Role("Администратор2");
		final List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		context.checking(new Expectations() {
			{
				oneOf(roleGenericDAO).readSubstances();
				will(returnValue(roles));
			}
		});
		RoleService roleService = new RoleService();
		roleService.setRoleGenericDao(roleGenericDAO);
		roleService.readRole();
	}

	@Test
	public void testFindRole() {
		final String name = "Продавец";
		final Role role = new Role("Продавец");
		context.checking(new Expectations() {
			{
				oneOf(roleGenericDAO).findSubstance(name);
				will(returnValue(role));
			}
		});
		RoleService roleService = new RoleService();
		roleService.setRoleGenericDao(roleGenericDAO);
		roleService.findRole(name);
	}

	@Test
	public void testAddRole() {
		final Role role = new Role("Администратор");
		context.checking(new Expectations() {
			{
				oneOf(roleDAO).addSubstance(role);

			}
		});
		RoleService roleService = new RoleService();
		roleService.setRoleDao(roleDAO);
		roleService.addRole("Администратор");
	}

	@Test
	public void testRemoveRole() {
		final String name = "Продавец";
		final Role role = new Role("Продавец");
		context.checking(new Expectations() {
			{
				oneOf(roleGenericDAO).removeSubstance(name);

			}
		});
		RoleService roleService = new RoleService();
		roleService.setRoleGenericDao(roleGenericDAO);
		roleService.removeRole("Продавец");
	}

}