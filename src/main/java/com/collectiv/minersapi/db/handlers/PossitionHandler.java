/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.Possition;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class PossitionHandler {
    
    @PersistenceContext
    EntityManager em;
    
    
    public Possition getPossition(String possition){
        Possition poss;
        try{
            poss = (Possition) em.createNamedQuery("Possition.findByPossition")
                .setParameter("possition", possition).getSingleResult();
        }catch(Exception e){
            throw new BadRequestException("Not A Valid user Possition", e);
        }
        
        return poss;
    }
    
    public List<Possition> getAllPossitions(){
        List<Possition> possitions;
        try{
            possitions = em.createNamedQuery("Possition.findAll").getResultList();
        }catch(Exception e){
            throw new InternalServerErrorException("Could not get possitions", e);
        }
        return possitions;
    }
    
    
}
