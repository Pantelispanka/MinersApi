/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.dto;

/**
 *
 * @author pantelispanka
 */
public class ApplicationDetails {
    
    private String role;
    private String possition;

    
    
    public ApplicationDetails(String role){
        this.role = role;
    }

    public ApplicationDetails() {
    }
    
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPossition() {
        return possition;
    }

    public void setPossition(String possition) {
        this.possition = possition;
    }
    
    
}
