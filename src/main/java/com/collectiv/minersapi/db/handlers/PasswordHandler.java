/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.filters.ApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author pantelispanka
 */
@Stateless
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
//        Object pass = null;
        try {            
            pass = (UserPasswords) em.createNamedQuery("UserPasswords.findByUserId")
                    .setParameter("userId", userId)
                    .getSingleResult();
//               Query q = (Query) em.createNativeQuery("SELECT * FROM user_passwords WHERE user_passwords.user_id = :user_id");
//               q.setParameter("user_id", userId);
//               pass =  q.getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            LOG.log(Level.WARNING, "There is no password for the user");
            throw new BadRequestException("There is no password for the user", e);
        }
        return pass;

    }
    
    
//    public UserPasswords getPassword(int userId){
//        Query q = (Query) em.createNativeQuery("SELECT * FROM user_passwords WHERE USER_ID = :ID");
//        q.setParameter("ID", userId);
//        UserPasswords userPass = (UserPasswords) q.getSingleResult();
//        return userPass;
//    }
    
    
    public List<UserPasswords> getAllPasswords(){
        List<UserPasswords> pass = new ArrayList<>();
        pass = (List<UserPasswords>) em.createNamedQuery("UserPasswords.findAll")
                .getResultList();
        LOG.log(Level.FINE, "All Passwords Requested!");
        return pass;
    }
    
    public List<UserPasswords> findAny(){
        return findAll();
    }
    
    
    public void updatePass(UserPasswords pass, MinersUsers user){
        try{
            UserPasswords passUpdate = (UserPasswords) em.createNamedQuery("UserPasswords.findByPassword").setParameter("password", pass).getSingleResult();
            passUpdate.setUserId(user);
            em.merge(passUpdate);
        }catch(Exception e){
            throw new BadRequestException("User FK Could not be created", e);
        }
    }
    
    public void createPass(UserPasswords pass){
        try{
            em.persist(pass);
        }catch(Exception e){
            throw new BadRequestException("Password could Not Be Created", e);
        }
    }
    
    public void createPassword(UserPasswords pass){
        try{
            em.persist(pass);
        }catch(Exception e){
            throw new BadRequestException("Password couldn not be created", e);
        }
    }
    
    
    
    
    

}
