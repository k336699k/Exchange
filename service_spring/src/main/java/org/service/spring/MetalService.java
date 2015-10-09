package org.service.spring;

import java.util.List;

import org.entity.Metal;


public interface MetalService {

	Metal getMetal(String metalTitle);

	void deleteMetal(String metalTitle);

	boolean addMetal(String metalTitle, String quantity, String priceString);

	List<Metal> getAllMetals();

}
