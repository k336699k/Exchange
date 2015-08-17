package org.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.dao.database.dao.GenericDao;
import org.dao.database.dao.MetalDao;
import org.entity.Metal;
import org.entity.User;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

public class MetalServiceTest {
	
	private Mockery context = new JUnit4Mockery();
	final GenericDao <Metal> metalGenericDAO= context.mock(GenericDao.class);
	
	@Test
	public void testReadSubstances() {
	 
		final Metal metal1 = new Metal("Арматура 18", "2 т", 3000);
		final Metal metal2 = new Metal("Арматура 24", "2 т", 3000);
		final List<Metal> metals = new ArrayList<Metal>();
		metals.add(metal1);
		metals.add(metal2);
		context.checking(new Expectations() {
			{
				oneOf(metalGenericDAO).readSubstances();
				will(returnValue(metals));
			}
		});
		MetalService metalService = new MetalService();
	    metalService.setMetalGenericDao(metalGenericDAO);
	    metalService.readSubstances();
		
	}
	
	@Test
	public void testFindSubstance() {
		final String title = "Арматура 19";
		final Metal metal = new Metal ("Арматура 19", "2 т", 3000);
		context.checking(new Expectations() {
			{
				oneOf(metalGenericDAO).findSubstance(title);
				will(returnValue(metal));
			}
		});
		MetalService metalService = new MetalService();
		Metal newMetal = new Metal();
		metalService.setMetalGenericDao( metalGenericDAO);
		newMetal  = metalService.findSubstance(title);
	
	}
	@Test
	public void testAddMetal() {
		final Metal metal = new Metal ("Арматура 19", "2 т", 3000);
		context.checking(new Expectations() {
			{
				oneOf(metalGenericDAO).addSubstance(metal);
						
			}
			});
		    MetalService metalService = new MetalService();
		    metalService.setMetalGenericDao( metalGenericDAO);
		    metalService.addMetal("Арматура 19", "2 т", 3000);
	  	}
	@Test
	public void testRemoveSubstance() {
		final Metal metal = new Metal ("Арматура 19", "2 т", 3000);
		final String title = "Арматура 19";
		context.checking(new Expectations() {
			{
				oneOf(metalGenericDAO).removeSubstance(title);
						
			}
			});
		    MetalService metalService = new MetalService();
		    metalService.setMetalGenericDao( metalGenericDAO);
		    metalService.removeSubstance(title);
	  	}
		
}
