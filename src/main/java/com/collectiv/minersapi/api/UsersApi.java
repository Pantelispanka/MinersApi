/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.api;

import com.collectiv.minersapi.db.handlers.UserHandler;
import com.collectiv.minersapi.db.handlers.UserInfHandler;
import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.db.models.UserInf;
import com.collectiv.minersapi.dto.ErrorReport;
import com.collectiv.minersapi.dto.MinersUser;
import com.collectiv.minersapi.facade.UsersFacade;
import com.collectiv.minersapi.filters.RoleEnum;
import com.collectiv.minersapi.filters.TokenSecured;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pantelispanka
 */
@Path("/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "/Users", tags = "Users")
public class UsersApi {

    @Inject
    UsersFacade usersFacade;
    
    @Inject
    UserInfHandler infHandler; 

    @GET
    @Path("/getallusers")
    @Produces("application/json")
    @ApiOperation(value = "Get All Users", response = MinersUser.class, responseContainer="List")
    public Response getUsers() {
        List<MinersUser> Users;
        try {
            Users = usersFacade.getAllUsers();
        } catch (Exception e) {
            ErrorReport erm = new ErrorReport();
            erm.setDevMessage(e.getMessage());
            erm.setErrorMessage(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(erm).build();
        }
        return Response.ok(Users).build();
    }

    @POST
    @Path("/createuser")
    @Produces("application/json")
    @ApiOperation(value = "Create A User", notes = "Create a User under supervisor only privilege")
    @ApiResponses( value = {@ApiResponse(code = 202, message = "User Created" ,response = MinersUser.class) ,
            @ApiResponse(code = 400, message = "User Could Not Be Created" , response = ErrorReport.class),
            @ApiResponse(code = 401, message = "Anouthorized", response = ErrorReport.class)} )
//    @TokenSecured({RoleEnum.ADMNISTRATOR})
    public Response createUser(MinersUser user) {
        if(!"ADMINISTRATOR".equals(user.getRole().toString()) 
                && !"DEFAULT_USER".equals(user.getRole().toString()) ){
            throw new BadRequestException("User Possition Not Verified or given wrong");
        }
        if(!"SIMPLE".equals(user.getPosition().toString()) 
                && !"HEAD".equals(user.getPosition().toString())){
            throw new BadRequestException("User Role Not Verified or given wrong");
        }
        if(user.getPassword().length() < 4){
            throw new BadRequestException("The Password you provided is wrong");
        }
        
        MinersUser userCreated = usersFacade.createMinersUser(user);
        
        return Response.accepted(userCreated).build();

    }
    
    

}
