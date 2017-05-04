/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi;

import com.collectiv.minersapi.api.ApplicationDetailsApi;
import com.collectiv.minersapi.api.AuthenticationApi;
import com.collectiv.minersapi.api.UsersApi;
import com.collectiv.minersapi.filters.CorsRequestFilter;
import com.collectiv.minersapi.filters.CorsResponseFilter;
import com.collectiv.minersapi.filters.ExceptionProvider;
import com.collectiv.minersapi.filters.TokenRequestFilter;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



/**
 *
 * @author pantelispanka
 */
@ApplicationPath("/api")
public class ApplicationMiners extends Application {

    public ApplicationMiners() {

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/MinersApi/api");
        beanConfig.setResourcePackage("com.collectiv.minersapi.api");
        beanConfig.setScan(true);

    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();

        resources.add(UsersApi.class);
        resources.add(AuthenticationApi.class);
        resources.add(ApplicationDetailsApi.class);

        resources.add(SwaggerSerializers.class);
        resources.add(ApiListingResource.class);
        
        resources.add(CorsRequestFilter.class);
        resources.add(CorsResponseFilter.class);
        
        resources.add(TokenRequestFilter.class);

        resources.add(ExceptionProvider.class);
                
        return resources;

    }

}
