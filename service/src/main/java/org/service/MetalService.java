package org.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dao.database.dao.GenericDao;
import org.dao.database.dao.MetalDao;
import org.dao.database.dao.UserDao;
import org.dao.database.dao.UserDaoInterface;
import org.entity.Metal;
import org.entity.User;
import org.exception.ServiceException;

public class MetalService implements MetalServiceInterface {
	private GenericDao<Metal> metalGenericDao = null;
	private static final Logger LOGGER = Logger.getLogger(MetalService.class);
	
	
	
	public MetalService() {
		metalGenericDao = MetalDao.getInstance();
	}



	public GenericDao<Metal> getMetalGenericDao() {
		if (metalGenericDao == null){
			LOGGER.error("I could not create MetalDAO. MetalDAO in null.");
			new ServiceException("I could not create MetalDAO. MetalDAO in null.");
		}
		return metalGenericDao;
	}



	public void setMetalGenericDao(GenericDao<Metal> metalGenericDao) {
		this.metalGenericDao = metalGenericDao;
	}



	@Override
	public void addMetal(String title, String quantity, int price) {
		Metal metal = new Metal(title, quantity, price);
		getMetalGenericDao().addSubstance(metal);
		LOGGER.info("Metal added, its name: " + title);
	}
	
	

	@Override
	public Metal findSubstance(String title) {
		LOGGER.info("Metal found, its name: " + title);
		return getMetalGenericDao().findSubstance(title);
		
	}

	@Override
	public List<Metal> readSubstances() {
		ArrayList<Metal> metals = (ArrayList<Metal>) getMetalGenericDao().readSubstances();
		LOGGER.info("launched method readSubstances() in MetalService");
		return metals;
	}

	@Override
	public void removeSubstance(String title) {
		getMetalGenericDao().removeSubstance(title);
		LOGGER.info("launched method removeSubstance() in MetalService");
	}

}
