package com.cosioguille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosioguille.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select case when count(*) > 0 then 'true' else 'false' end as boolean "
			+ "from users u where u.username = :username")
    Boolean existsUserByUsername(@Param("username") String username);
	
	@Query("select case when count(*) > 0 then 'true' else 'false' end as boolean "
			+ "from users u where u.username = :username and u.password = :password")
    Boolean authenticate(@Param("username") String username, @Param("password")String password);
	
	User findByUsername(String username);

}