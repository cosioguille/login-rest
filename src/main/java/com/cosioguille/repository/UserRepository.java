package com.cosioguille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosioguille.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select case when count(*) > 0 then \"true\" else \"false\" end as boolean "
			+ "from users where username = :username and password = :password")
    public Boolean authenticate(@Param("username") String username, @Param("password")String password);

}