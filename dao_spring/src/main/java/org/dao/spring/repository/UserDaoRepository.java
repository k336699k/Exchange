package org.dao.spring.repository;




import java.util.Collection;

import org.dao.spring.pojo.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository("userDaoRepository")

public interface UserDaoRepository extends JpaRepository<UserPojo, Integer> {
	
	@Query("SELECT u FROM UserPojo u WHERE u.login =:userLogin AND u.password =:userPassword")
    UserPojo findByLoginAndPassword(@Param("userLogin") String userLogin, @Param("userPassword") String userPassword);
	
	@Query("SELECT u FROM UserPojo u WHERE u.login =:userLogin")
    UserPojo findByLogin(@Param("userLogin") String userLogin);
	
	@Query("SELECT b FROM UserPojo b INNER JOIN b.roles role WHERE role.name = :roleName")
	Collection getUserByRole(@Param("roleName") String roleName);

}



