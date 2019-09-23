package com.cosioguille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosioguille.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

}
