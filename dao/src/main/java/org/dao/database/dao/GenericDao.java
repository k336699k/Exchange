package org.dao.database.dao;

import java.util.List;

public interface GenericDao<T> {

	void addSubstance(T object);

	T findSubstance(String name);

	List<T> readSubstances();

	void removeSubstance(String name);

}
