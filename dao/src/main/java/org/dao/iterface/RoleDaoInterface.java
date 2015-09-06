package org.dao.iterface;

import java.sql.SQLException;
import java.util.Collection;


import org.entity.Role;

public interface RoleDaoInterface {
	
	void addSubstance(Role role) ;
	Collection getRoleByUser(String login) ;
	
}
