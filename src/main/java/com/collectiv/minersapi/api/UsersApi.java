/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.api;

import com.collectiv.minersapi.db.handlers.UserHandler;
import com.collectiv.minersapi.db.models.MinersUsers;
import com.collectiv.minersapi.dto.Credentials;
import com.collectiv.minersapi.dto.ErrorReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    UserHandler userHandler;

    

    @GET
    @Path("/getallusers")
    @Produces("application/json")
    @ApiOperation(value = "Get All Users", response = MinersUsers.class)
    public Response getUsers() {
        List<MinersUsers> Users = new ArrayList<>();
        try {
            Users = userHandler.getAllUsers();
        }catch(Exception e){
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
    @ApiOperation(value = "Create A User")
    public Response createUser(Credentials credentials) {
        return Response.accepted().build();

    }

}
