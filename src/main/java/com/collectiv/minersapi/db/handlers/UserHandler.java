/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.dto.Credentials;
import com.collectiv.minersapi.dto.MinersUser;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class UserHandler {

    @PersistenceContext
    EntityManager em;

    public MinersUsers getByEmail(String email ) {
        MinersUsers user = new MinersUsers();
        try{
            user = (MinersUsers) em.createNamedQuery("MinersUsers.findByEmail")
                .setParameter("email", email)
                .getSingleResult();
        }catch(NoResultException e){
            throw new NoResultException("Wrong Email Provided" + e);
        }
        return user;   
    }

    public List<MinersUsers> getAllUsers() {
        List<MinersUsers> AllUsers = null;
        try {
            AllUsers = em.createNamedQuery("MinersUsers.findAll").getResultList();
        }catch(Exception e){
             throw new InternalServerErrorException(e.getMessage());
        }
        
        return AllUsers;
    }

}
