/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.filters.ApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class PasswordHandler extends AbstractDbHandler<UserPasswords>{

    private static final Logger LOG = Logger.getLogger(PasswordHandler.class.getName());

    public PasswordHandler() {
        super(UserPasswords.class);
    }
    
    
    
    @Inject
    UserHandler userHandler;
    
//    @Override
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    
    public UserPasswords getPassword(int userId) throws BadRequestException {
//        UserPasswords pass = new UserPasswords();
        UserPasswords pass = new UserPasswords();
        try {            
            pass = (UserPasswords) em.createNamedQuery("UserPasswords.findByUserId")
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            LOG.log(Level.WARNING, "There is no password for the user");
            throw new BadRequestException("There is no password for the user", e);
        }
        return pass;

    }
    
    
    public List<UserPasswords> getAllPasswords(){
        List<UserPasswords> pass = new ArrayList<>();
        pass = (List<UserPasswords>) em.createNamedQuery("UserPasswords.findAll")
                .getResultList();
        LOG.log(Level.ALL, "All Passwords Requested!");
        return pass;
    }
    
    public List<UserPasswords> findAny(){
        return findAll();
    }
    
    
    
    
    

}
