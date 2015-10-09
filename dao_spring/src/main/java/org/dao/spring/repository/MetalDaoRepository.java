package org.dao.spring.repository;


import org.dao.spring.pojo.MetalPojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("metalDaoRepository")
public interface MetalDaoRepository extends JpaRepository<MetalPojo, Integer> {
	
	@Query("SELECT u FROM MetalPojo u WHERE u.title =:metalTitle")
    MetalPojo findByName(@Param("metalTitle") String metalTitle);

}
