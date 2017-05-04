/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.facade;

import com.collectiv.minersapi.db.handlers.PossitionHandler;
import com.collectiv.minersapi.db.handlers.UserRoleHandler;
import com.collectiv.minersapi.db.models.Possition;
import com.collectiv.minersapi.db.models.UserRole;
import com.collectiv.minersapi.dto.ApplicationDetails;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author pantelispanka
 */

@RequestScoped
public class ApplicationDetailsFacade {
    
    @Inject
    UserRoleHandler userRoleHandler;
    
    @Inject
    PossitionHandler posHandler;
    
    
    public List<ApplicationDetails> getAllRoles(){
        List<UserRole> roles = userRoleHandler.getUserRoles();
        List<ApplicationDetails> details = new ArrayList();
        
        String addRole;
        for(UserRole role : roles){
            addRole = role.getRole().toString();
            ApplicationDetails ad = new ApplicationDetails();
            ad.setRole(addRole);
            details.add(ad);
        }
        return details;
    }
    
    public List<ApplicationDetails> getAllPossitions(){
        List<Possition> possitions = posHandler.getAllPossitions();
        List<ApplicationDetails> details = new ArrayList();
        
        String addpossition;
        for(Possition poss : possitions){
            addpossition = poss.getPossition().toString();
            ApplicationDetails ad = new ApplicationDetails();
            ad.setPossition(addpossition);
            details.add(ad);
        }
        return details;
    }
    
}
