package org.service.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.dao.spring.pojo.MetalPojo;
import org.dao.spring.pojo.UserPojo;
import org.dao.spring.repository.MetalDaoRepository;
import org.dao.spring.repository.UserDaoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class MetalServiceTest {
private Mockery context = new JUnit4Mockery();
	
	private MetalDaoRepository metalDaoRepository = context.mock(MetalDaoRepository.class);

	@Test
	public void testGetMetal() {
	
		final String name = "Арматура 14";
		final MetalPojo metal = new MetalPojo("Арматура 14", "2 т", 3000);
		context.checking(new Expectations() {
			{
				oneOf(metalDaoRepository).findByName(name);
				will(returnValue(metal));
			}
		});
		MetalServiceImpl metalService = new MetalServiceImpl ();
		metalService.setMetalDaoRepository(metalDaoRepository);
		assertNotNull(metalService.getMetal(name));
		
	}
	
	
	
	@Test
	public void testGetAllMetals() {
		final List metals = new ArrayList<MetalPojo>();
		metals.add(new MetalPojo("Арматура 14", "2 т", 3000));
		metals.add(new MetalPojo("Арматура 12", "3 т", 4000));
		context.checking(new Expectations() {
			{
				oneOf(metalDaoRepository).findAll();
				will(returnValue(metals));
			}
		});
		MetalServiceImpl metalService = new MetalServiceImpl ();
		metalService.setMetalDaoRepository(metalDaoRepository);
	
		assertEquals(2, metalService.getAllMetals().size());
	}
}
