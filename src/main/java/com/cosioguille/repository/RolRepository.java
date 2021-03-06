package com.cosioguille.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosioguille.model.Rol;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
