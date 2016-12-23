/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.facade;

import com.collectiv.minersapi.db.handlers.PasswordHandler;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class PasswordsFacade {
    
    
    
    
    
    @Inject 
    PasswordHandler passHandler;
    
    
    
}
