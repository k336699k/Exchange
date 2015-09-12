package org.service;

import java.util.List;

import org.entity.Metal;

public interface MetalServiceInterface {

	boolean addMetal(String title, String quantity, int price);

	Metal findSubstance(String title);

	List<Metal> readSubstances();

	void removeSubstance(String title);

}
