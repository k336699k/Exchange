package org.dao.iterface;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

	T findSubstance(String name) ;

	List<T> readSubstances() ;

	void removeSubstance(String name) ;

}
