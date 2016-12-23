/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.facade;

import com.collectiv.minersapi.db.handlers.ItemStatusHandler;
import com.collectiv.minersapi.db.handlers.PasswordHandler;
import com.collectiv.minersapi.db.handlers.UserHandler;
import com.collectiv.minersapi.db.models.ItemStatus;
import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.dto.Credentials;
import com.collectiv.minersapi.filters.ApplicationException;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.BadRequestException;
;

/**
 *
 * @author pantelispanka
 */

@RequestScoped
public class AuthenticationFacade {
    
    @Inject
    UserHandler usersHandler;
    
    @Inject
    PasswordHandler passwordsHandler;
    
    
    private static ConcurrentHashMap ApiKeyMap;
    
    static{
        ApiKeyMap = new ConcurrentHashMap<>();
    }
    
    public String UserAuthentication(Credentials credentials) throws ApplicationException {
        
        MinersUsers user = new MinersUsers();
        UserPasswords pass = new UserPasswords();
        
        String emailToCheck = credentials.getEmail();
        String passwordToCheck = credentials.getPassword();
        try{
            user = usersHandler.getByEmail(emailToCheck);
        }catch(NoResultException e){
            throw new BadRequestException("The Email you provided could't be found"
                    + ". Please register if you haven't allready or contact for help");
        }
        
        
        int user_id = user.getId();
        pass = passwordsHandler.getPassword(user_id);
        
        String userStatus = user.getItemStatus().getStatus();
        String userRole = user.getUserRole().getRole();
        String userPos = user.getUserPossition().getPossition();
        
        return "Reached";
        
        
    }
    
    
}
