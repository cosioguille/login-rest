package com.cosioguille.service;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.cosioguille.model.Rol;
import com.cosioguille.repository.RolRepository;
 
@Service
@Transactional
public class RolServiceImp implements RolService {
 
   @Autowired
   private RolRepository rolRepository;
 
   @Override
   public List<Rol> getRoles() {
      return rolRepository.findAll();
   }
 
   @Override
    public Rol saveRol(Rol rol) {
       return (Rol) rolRepository.save(rol);
    }
   
   @Override
    public Rol getRol(int id) {
       return rolRepository.findById(id).get();
    }
 
    @Override
    public void updateRol(int id, Rol rol) {
        rol.setId(id);
        rolRepository.save(rol);
    }
   
    @Override
    public void deleteRol(int id) {
        rolRepository.deleteById(id);
    }
    
    @Override
    public boolean existsRol(int id) {
    	return rolRepository.existsById(id);
    }
}