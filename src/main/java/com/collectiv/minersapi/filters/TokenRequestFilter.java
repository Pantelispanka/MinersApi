/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.filters;

import com.collectiv.minersapi.facade.AuthenticationFacade;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.management.relation.Role;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pantelispanka
 */
@TokenSecured
@Provider
@Priority(Priorities.AUTHORIZATION)
@Produces("application/json")
public class TokenRequestFilter implements ContainerRequestFilter, Serializable {

    @Inject
    AuthenticationFacade authenticationFacade;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<RoleEnum> classRoles = extractRoles(resourceClass);

        MultivaluedMap headers = requestContext.getHeaders();
        String api_key;
        try {
            api_key = headers.get("api_key").toString();
            api_key = api_key.substring(1, api_key.length() - 1);
        } catch (Exception e) {
            throw new BadRequestException("No Api Key Provided. Please Login First");
        }

        authenticationFacade.apiKeyCheck(api_key);
        checkPermissions(classRoles, api_key);

    }

    public List<RoleEnum> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<RoleEnum>();
        } else {
            TokenSecured secured = annotatedElement.getAnnotation(TokenSecured.class);
            if (secured == null) {
                return new ArrayList<RoleEnum>();
            } else {
                RoleEnum[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    public void checkPermissions(List<RoleEnum> allowedRoles, String api_key) {
        Object Role = authenticationFacade.getUserRole(api_key);
        try {
            if(allowedRoles != null){
                allowedRoles.contains(Role);
            }
        } catch (Exception e) {
            throw new ForbiddenException("Your User doesn't have permission for the request", e);
        }

    }

}
