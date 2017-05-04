/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.dto;

import com.collectiv.minersapi.db.models.MinersUsers;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author pantelispanka
 */
public class MinersUser {
    
    private Integer id;
    private String email;
    
    private String username;
    private String role;
    private String status;
    private String position;
    private String password;
    
    public MinersUser(){}
    
    
    public MinersUser(MinersUsers user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getUserRole().getRole();
//        this.username = user.getUserInfCollection().toString();
        this.status = user.getItemStatus().getStatus();
        this.position = user.getUserPossition().getPossition();
    }
    
    
    public String getPassword() {
        return password;
    }
    
    @ApiModelProperty(required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    public String getRole() {
        return role;
    }

    @ApiModelProperty(example = "ADMINISTRATOR, DEFAULT_USER", required = true)
    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @ApiModelProperty(example = "SIMPLE, HEAD", required = true)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
    
    
}
