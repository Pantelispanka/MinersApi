/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.api;

import com.collectiv.minersapi.db.handlers.PasswordHandler;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.dto.Credentials;
import com.collectiv.minersapi.facade.AuthenticationFacade;
import com.collectiv.minersapi.filters.ApplicationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


/**
 *
 * @author pantelispanka
 */
@Consumes("application/json")
@Produces("application/json")
@Path("/authentication")
@Api(value = "/authenticate", tags = "Authentication")
public class AuthenticationApi {

    @Inject
    AuthenticationFacade authenticationFacade;

    @Inject
    PasswordHandler passHandler;
    
    
    @PUT
    @Path("/authenticateuser")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Authenticates user and returns an api key", responseContainer = "List")
//    @ApiResponses(code = '200', )
    public Response authenticateUser(Credentials credentials) {
        String mpla;
        try {
            mpla = authenticationFacade.UserAuthentication(credentials);

        }catch(Exception e){
            throw new BadRequestException(e);
        }
        return Response.ok(mpla).build();
    }
    
    @GET
    @Path("/userpasswords")
    @Produces("application/json")
    @ApiOperation(value = "Returns all user passwords", responseContainer = "List")
    public Response getAllPasswords(){
        List<UserPasswords> pass = passHandler.getAllPasswords();
        return Response.ok(pass).build();
    }
    
    @GET
    @Path("/password/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Returns a password by id")
    public Response getAllPasswords(@PathParam("id") int id) throws BadRequestException{
        UserPasswords pass = new UserPasswords();
        pass = passHandler.getPassword(id);
        return Response.ok().entity(pass).build();
    }

    
    @GET
    @Path("/errorhandling")
    @Produces("application/json")
    @ApiOperation(value = "Returns custom error")
    public Response getError() throws ApplicationException{
        Exception e = new BadRequestException();
        throw new ApplicationException("Exception with custom message", e);
    }
}
