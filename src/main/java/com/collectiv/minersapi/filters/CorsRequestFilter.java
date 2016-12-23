/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pkaratzas
 */
@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter{
    
    private static final Logger LOG = Logger.getLogger(CorsRequestFilter.class.getName());

    @Context
    SecurityContext securityContext;

    /**
     * Replies with Status 200 to all OPTION Requests.
     *
     * @param request
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String userName = securityContext.getUserPrincipal() != null ? securityContext.getUserPrincipal().getName() : "unkown";
        LOG.log(Level.INFO, "Request: {0} {1} by User {2}", new Object[]{request.getMethod(), request.getUriInfo().getRequestUri(), userName});

        LOG.log(Level.FINE, "Executing  CORS Request Filter");
        if (request.getRequest().getMethod().equals("OPTIONS")) {
            LOG.log(Level.FINE, "HTTP Method (OPTIONS) - Detected!");
            request.abortWith(Response.status(Response.Status.OK).build());
        }
    }
    
    
}
