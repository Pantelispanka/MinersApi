/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.UserInf;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class UserInfHandler {
    
    
    @PersistenceContext
    EntityManager em;
    
    
    public void creareUserInf(UserInf inf){
        em.persist(inf);
    }
    
}
