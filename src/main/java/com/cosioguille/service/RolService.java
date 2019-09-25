package com.cosioguille.service;

import java.util.List;

import com.cosioguille.model.Rol;

public interface RolService {
	
   public List<Rol> getRoles();
   
   public Rol saveRol(Rol rol);
 
   public Rol getRol(int id);
   
   public void updateRol(int id, Rol rol);
 
   public void deleteRol(int id);
   
   public boolean existsRol(int id);

}
