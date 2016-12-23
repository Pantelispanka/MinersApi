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
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pkaratzas
 */
@Provider
@PreMatching
public class CorsResponseFilter implements ContainerResponseFilter{
    
    
    
    private static final Logger LOG = Logger.getLogger(CorsResponseFilter.class.getName());

    /**
     * Filters all Responses from the Service in order to add CORS Headers.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        LOG.log(Level.FINE, "Executing CORS Response Filter for request {0}", request.getUriInfo().getPath());
        for (String header : request.getHeaders().keySet()) {
            LOG.log(Level.FINE, "{0}:{1}", new Object[]{header, request.getHeaders().getFirst(header)});
        }
        response.getHeaders().add("Access-Control-Allow-Origin", request.getHeaderString("origin") != null ? request.getHeaderString("origin") : "localhost");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Headers", "origin, credentials, authorization, accept, content-type");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }
    
    
    
}
