package org.service.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;


import org.dao.spring.pojo.RolePojo;

import org.dao.spring.repository.RoleDaoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class RoleServiceTest {
private Mockery context = new JUnit4Mockery();
	
	private RoleDaoRepository roleDaoRepository = context.mock(RoleDaoRepository.class);

	@Test
	public void testGetRole() {
	
		final String name = "Продавец";
		final RolePojo role = new RolePojo("Продавец");
		context.checking(new Expectations() {
			{
				oneOf(roleDaoRepository).findByName(name);
				will(returnValue(role));
			}
		});
		RoleServiceImpl roleService = new RoleServiceImpl ();
		roleService.setRoleDaoRepository(roleDaoRepository);
		assertNotNull(roleService.getRole(name));
		
	}
	
		
	@Test
	public void testGetAllRoles() {
		final List roles = new ArrayList<RolePojo>();
		roles.add(new RolePojo("Продавец"));
		roles.add(new RolePojo("Голодранец"));
		context.checking(new Expectations() {
			{
				oneOf(roleDaoRepository).findAll();
				will(returnValue(roles));
			}
		});
		RoleServiceImpl roleService = new RoleServiceImpl ();
		roleService.setRoleDaoRepository(roleDaoRepository);
	
		assertEquals(2, roleService.getAllRoles().size());
	}
}