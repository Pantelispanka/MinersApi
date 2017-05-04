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
public class ErrorReport {
    
//    private Integer errorId;
    private Integer status;
    private String errorMessage;
    private String DevMessage;

//    public Integer getErrorId() {
//        return errorId;
//    }
//
//    public void setErrorId(Integer errorId) {
//        this.errorId = errorId;
//    }
//
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDevMessage() {
        return DevMessage;
    }

    public void setDevMessage(String DevMessage) {
        this.DevMessage = DevMessage;
    }
    
    
    
    
    
}
