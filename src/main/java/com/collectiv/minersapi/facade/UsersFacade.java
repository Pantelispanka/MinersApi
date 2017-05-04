/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.facade;

import com.collectiv.minersapi.db.handlers.PasswordHandler;
import com.collectiv.minersapi.db.handlers.PossitionHandler;
import com.collectiv.minersapi.db.handlers.UserHandler;


import com.collectiv.minersapi.db.handlers.UserRoleHandler;
import com.collectiv.minersapi.db.models.ItemStatus;
import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.Possition;
import com.collectiv.minersapi.db.models.UserInf;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.db.models.UserRole;
import com.collectiv.minersapi.dto.MinersUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.ws.rs.BadRequestException;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class UsersFacade {
    
    
    @EJB
    UserHandler userHandler;
    
    @Inject
    PossitionHandler possitionHandler;
    
    @Inject
    UserRoleHandler userRoleHandler;
    
    @EJB
    PasswordHandler passHandler;
    
    
    @PrePersist
    public void initializeUserPassword(){
        
    }
    
    public MinersUser createMinersUser(MinersUser user) throws BadRequestException{
        Possition poss = possitionHandler.getPossition(user.getPosition().toString());
        UserRole role = userRoleHandler.getUserRole(user.getRole().toString());
        MinersUsers minersUser = new MinersUsers();
        minersUser.setEmail(user.getEmail().toString());
        minersUser.setDateCreated(new Date(System.currentTimeMillis()));
        minersUser.setUserPossition(poss);
        minersUser.setUserRole(role);
        ItemStatus status = new ItemStatus();
        status.setId(1);
        status.setStatus("ACTIVE".toString());
        minersUser.setItemStatus(status);
        UserInf inf = new UserInf();
        List<UserInf> infcol = new ArrayList();
        inf.setUserName(user.getUsername());
        infcol.add(inf);
        minersUser.setUserInfCollection(infcol);;
        UserPasswords pass = new UserPasswords();
        pass.setPassword(user.getPassword());
        pass.setUserId(minersUser);
        minersUser.setUserPassword(pass);
        
        
        userHandler.createUser(minersUser);
        
        List<MinersUsers> userCreatedCheck = userHandler.getByEmail(minersUser.getEmail());
        MinersUser userCreated = new MinersUser();
        MinersUsers userFromList = userCreatedCheck.get(0);
        userCreated.setEmail(userFromList.getEmail());
        userCreated.setId(userFromList.getId());
//        userCreated.setUsername();
        userCreated.setStatus(userFromList.getItemStatus().getStatus());
        userCreated.setRole(userFromList.getUserRole().getRole());
        userCreated.setPosition(userFromList.getUserPossition().getPossition());
        
        return userCreated;
        
           
    }
    
    public List<MinersUser> getAllUsers(){
        List<MinersUsers> users = userHandler.getAllUsers();
        List<MinersUser> listedUsers = users.stream()
                .map(MinersUser::new)
                .collect(Collectors.toList());
        return listedUsers;
    }
    
    
    public List<UserRole> getAllUserRoles(){
        List<UserRole> roles = userRoleHandler.getUserRoles();
        return roles;
    }
    
    
}
