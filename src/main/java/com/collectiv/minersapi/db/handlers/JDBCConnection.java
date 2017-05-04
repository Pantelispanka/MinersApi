/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.db.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author pantelispanka
 */
@RequestScoped
public class JDBCConnection {

//    static final String DRIVER = ""
//    Class.forName ("org.postgresql.Driver");
    public Connection getCon() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.100:5432/pantelispanka", "pantelispanka", "kapan1");

            return connection;
        }catch (ClassNotFoundException | SQLException e){
            throw new InternalServerErrorException("Couldn't establish Connection With Database", e);
        }

    }

}
