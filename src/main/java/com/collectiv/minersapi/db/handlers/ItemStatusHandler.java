/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import com.collectiv.minersapi.db.models.ItemStatus;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pantelispanka
 */
public class ItemStatusHandler extends AbstractDbHandler<ItemStatus>{
    
    @PersistenceContext
    EntityManager em;
    
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    
    public ItemStatusHandler(){
        super(ItemStatus.class);
    }
    
    public String getStatusById(int Id){
        String status = (String) em.createNamedQuery("ItemStatus.findById")
                .setParameter("Id", Id)
                .getSingleResult();
        return status;
    }
    
    
}
