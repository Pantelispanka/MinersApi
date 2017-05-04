/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.api;

import com.collectiv.minersapi.db.handlers.PasswordHandler;
import com.collectiv.minersapi.db.models.UserPasswords;
import com.collectiv.minersapi.dto.ApiKey;
import com.collectiv.minersapi.dto.Credentials;
import com.collectiv.minersapi.dto.ErrorReport;
import com.collectiv.minersapi.facade.AuthenticationFacade;
import com.collectiv.minersapi.filters.ApplicationException;
import com.collectiv.minersapi.filters.RoleEnum;
import com.collectiv.minersapi.filters.TokenSecured;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses(value = { @ApiResponse(code = 200 , message = "User Authenticated", response = ApiKey.class),
            @ApiResponse(code = 400 , message = "Wrong Credentials", response = ErrorReport.class),
            @ApiResponse(code = 500, message = "Unkown Error", response = ErrorReport.class)} )
    public Response authenticateUser(Credentials credentials) throws BadRequestException{
        ApiKey apiKey = new ApiKey();        
        apiKey = authenticationFacade.UserAuthentication(credentials); 
        return Response.ok(apiKey).build();
    }
    
    @GET
    @Path("/userpasswords")
    @Produces("application/json")
    @ApiOperation(value = "Returns all user passwords", responseContainer = "List")
    @TokenSecured({RoleEnum.ADMNISTRATOR})
    public Response getAllPasswords(){
        List<UserPasswords> pass = passHandler.getAllPasswords();
        return Response.ok(pass).build();
    }
    
    @GET
    @Path("/password/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Returns a password by id")
    @TokenSecured({RoleEnum.ADMNISTRATOR})
    public Response getPasswordById(@PathParam("id") int id) throws BadRequestException{
        UserPasswords pass = passHandler.getPassword(id);
        return Response.ok().entity(pass).build();
    }

    
}
