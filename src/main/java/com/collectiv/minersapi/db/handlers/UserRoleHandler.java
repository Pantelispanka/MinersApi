/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author pantelispanka
 */
@Stateless
public class UserRoleHandler {
    
    @PersistenceContext
    EntityManager em;
    
    
    public UserRole getUserRole(String role){
        UserRole userRole;
        try{
            userRole = (UserRole) em.createNamedQuery("UserRole.findByRole").setParameter("role", role)
                    .getSingleResult();
        }catch(Exception e){
            throw new BadRequestException("Role Could not be veridied", e);
        }
        return userRole;
    }
    
    
    public List<UserRole> getUserRoles(){
        List<UserRole> roles;
        try{
            roles = em.createNamedQuery("UserRole.findAll").getResultList();
        
        }catch(Exception e){
            throw new InternalServerErrorException("Error In Getting the user roles", e);
        }
        return roles;
    }
    
    
}
