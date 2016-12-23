/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.filters;

import java.io.Serializable;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author pantelispanka
 */
public class ApplicationException extends Exception implements Serializable {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String msg, Exception e) {
        super(msg, e);

    }

}
