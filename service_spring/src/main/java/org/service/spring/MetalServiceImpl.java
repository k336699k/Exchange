package org.service.spring;

import java.util.List;

import org.apache.log4j.Logger;
import org.dao.spring.repository.MetalDaoRepository;

import org.entity.Metal;

import org.service.spring.convert.ConvetrToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("metalServiceImpl")
@Transactional
public class MetalServiceImpl implements MetalService {

	private static final Logger LOGGER = Logger.getLogger(MetalServiceImpl.class);
	@Autowired
	private MetalDaoRepository metalDaoRepository;
	
		
	public MetalDaoRepository getMetalDaoRepository() {
		return metalDaoRepository;
	}

	public void setMetalDaoRepository(MetalDaoRepository metalDaoRepository) {
		this.metalDaoRepository = metalDaoRepository;
	}

	@Override
	public Metal getMetal(String metalTitle) {
		LOGGER.info("launched method getMetal() in MetalService");
		return ConvetrToClass.convetrToMetal(metalDaoRepository.findByName( metalTitle));
	}

	@Override
	public void deleteMetal(String metalTitle) {
		Metal metal = null;
		metal = getMetal(metalTitle);
		if (metal.getTitle() != null) {
			metalDaoRepository.delete(metal.getiD());
		}
		 LOGGER.info("launched method deleteMetal() in MetalService");
	}
		
	

	@Override
	public boolean addMetal(String metalTitle, String quantity, String priceString) {
		boolean flag = false;
		Metal metal = getMetal (metalTitle);
		if (metal.getTitle() == null) {
			
		Metal newMetal = new Metal (metalTitle,quantity,Integer.valueOf(priceString));
		metalDaoRepository.saveAndFlush(ConvetrToClass.convetrToMetalPojo(newMetal));
		flag = true;
		}
		
		 LOGGER.info("launched method addMetal() in MetalService");
		
		return flag;
	}

	@Override
	public List<Metal> getAllMetals() {
		LOGGER.info("launched method getAllMetals() in MetalService");
		return ConvetrToClass.convetrToMetalCollection(metalDaoRepository.findAll());
	}

}
