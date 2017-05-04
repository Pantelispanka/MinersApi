/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.UserPasswords;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.FlushModeType;

/**
 *
 * @author pantelispanka
 */
@Stateless
public class UserHandler extends AbstractDbHandler<MinersUsers> {

    private static final Logger LOG = Logger.getLogger(UserHandler.class.getName());
    
    @PersistenceContext(unitName = "com.collectiv_MinersApi_war_1.0PU" )
    private EntityManager em;

    public UserHandler() {
        super(MinersUsers.class);
    }
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }

    public List<MinersUsers> getByEmail(String email ) {
        List<MinersUsers> user = new ArrayList();
//        try{
            user = (List<MinersUsers>) em.createNamedQuery("MinersUsers.findByEmail")
                    .setParameter("email", email)
                    .getResultList();
//        }catch(NoResultException e){
//            throw new BadRequestException("Email Could Not Be Found", e);
//        }
        return user;   
    }

    public List<MinersUsers> getAllUsers() {
        LOG.log(Level.FINE,"{0}:{1}", "Method getAllUsers Reached");
        List<MinersUsers> AllUsers = null;
        try {
            AllUsers = em.createNamedQuery("MinersUsers.findAll").getResultList();
        }catch(Exception e){
             throw new InternalServerErrorException(e.getMessage());
        }
        
        return AllUsers;
    }
    
    public void createUser(MinersUsers user){
        LOG.log(Level.FINE, "{0}:{1}","Method Reached");
        try{
//            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//            Session session = sessionFactory.openSession();
//            session.save(user);
//            session.flush();
//            session.save(user.getUserPasswords());
//            
//            session.close();
//            em.getTransaction().begin();
//            em.detach(user.getUserPasswords());
            em.merge(user);
//            em.persist(user);
//            em.getTransaction().commit();
//            em.close();
            
//            em.flush();
//            UserPasswords pass = new UserPasswords();
//            
//            pass.setPassword(user.getUserPasswords().getPassword().toString());
//            pass.setUserId(user);
//            em.merge(pass);
//            em.persist(user.getUserPasswords());
            
//            em.flush();
        }catch(Exception e){
            throw new BadRequestException("User Could not be created", e);
        }
    }

}
