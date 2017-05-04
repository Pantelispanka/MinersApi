/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.filters;

import com.collectiv.minersapi.dto.ErrorReport;
import java.util.Arrays;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pantelispanka
 */
@Provider
public class ExceptionProvider implements ExceptionMapper<Exception>{
    
    @Override
    public Response toResponse(Exception exception){
                ErrorReport er = new ErrorReport();

        Exception exc = exception;
        String excClassName = exc.getClass().getName();
        String[] excClass = excClassName.split("\\.");
        String excName = excClass[(excClass.length - 1)].toUpperCase();
        
        switch(excName){
            case "BADREQUESTEXCEPTION": 
                er.setStatus(400);
                er.setDevMessage(Arrays.toString(exception.getStackTrace()));
                er.setErrorMessage(exception.getMessage().toString());
                return Response.status(Status.BAD_REQUEST).entity(er).build();
            case "NOTAUTHORIZEDEXCEPTION":
                er.setStatus(404);
                er.setDevMessage(Arrays.toString(exception.getStackTrace()));
                er.setErrorMessage(exception.getMessage().toString());
                return Response.status(Status.UNAUTHORIZED).entity(er).build();
            case "FORBIDDENEXCEPTION":
                er.setStatus(403);
                er.setDevMessage(Arrays.toString(exception.getStackTrace()));
                er.setErrorMessage(exception.getMessage().toString());
                return Response.status(Status.FORBIDDEN).entity(er).build();

        }
        er.setStatus(500);
        er.setDevMessage(Arrays.toString(exception.getStackTrace()));
        er.setErrorMessage("Unknown Error");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(er).build();
        
    }
    
}
