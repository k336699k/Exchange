package org.dao.spring.repository;

import java.util.Collection;

import org.dao.spring.pojo.RolePojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("roleDaoRepository")
public interface RoleDaoRepository extends JpaRepository<RolePojo, Integer> {
	
	@Query("SELECT u FROM RolePojo u WHERE u.name =:roleName")
    RolePojo findByName(@Param("roleName") String roleName);
	
	@Query("SELECT b FROM RolePojo b INNER JOIN b.users user WHERE user.login = :userLogin")
	Collection getRoleByUser (@Param("userLogin") String userLogin);

}
