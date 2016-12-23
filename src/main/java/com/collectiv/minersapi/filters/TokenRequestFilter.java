/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.filters;

import java.io.Serializable;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pantelispanka
 */
@TokenSecured
@Provider
@Priority(Priorities.AUTHENTICATION)
@Produces("application/json")
public class TokenRequestFilter implements ContainerRequestFilter, Serializable{

    @Override
    public void filter(ContainerRequestContext requestContext) {

        MultivaluedMap headers = requestContext.getHeaders();

        Object api_key = headers.get("api_key");

    }

}
