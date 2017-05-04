/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.api;

import com.collectiv.minersapi.db.models.UserRole;
import com.collectiv.minersapi.dto.ApplicationDetails;
import com.collectiv.minersapi.facade.ApplicationDetailsFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author pantelispanka
 */

@Path("/applicationdetails")
@Consumes("application/json")
@Produces("application/json")
@Api(value = "/applicationdetails", tags = "Details")
public class ApplicationDetailsApi {
 
    
    
    @Inject
    ApplicationDetailsFacade detailsFacade;
    
//    @Inject
//    ApplicationDetailsFacade applicationDetailsFacade;
    
    
    @GET
    @Path("/role")
    @Produces("application/json")
    @ApiOperation("Gets the possible roles of the users of the app")
    public Response getDetails(){
        List<ApplicationDetails> roles;
        try{
            roles = detailsFacade.getAllRoles();
        }catch(Exception e){
            throw new InternalServerErrorException(e);
        }
        
        return Response.ok(roles).build();
    }
    
    @GET
    @Path("/possitions")
    @Produces("application/json")
    @ApiOperation("Gets the possible roles of the users of the app")
    public Response getPossitions(){
        List<ApplicationDetails> possitions;
        try{
            possitions = detailsFacade.getAllPossitions();
        }catch( Exception e){
            throw new InternalServerErrorException(e);
        }
        return Response.ok(possitions).build();
    }
    
    
}
