/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectiv.minersapi.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author pantelispanka
 */
@Singleton
@Startup
public class OnAppStart {

    private static InetAddress ip;
    private static String dbname;
    private static String dbuser;
    private static String dbpass;
    private static String dburl;
    private static String dbdriver;
    private static String dbjndi;
//    private static File sqlScripts;

    private static String administrator;
    private static String admpass;
    private static String itemstatuscreation;
    private static String possitioncreation;
    private static String rolecreation;

    private static Properties prop;

    @PostConstruct
    public void properties() {
        InputStream properties = null;

        ResourceBundle minersProp = ResourceBundle.getBundle("miners");
        ResourceBundle minersSqlCreation = ResourceBundle.getBundle("MinersBasisSql");

        dbname = minersProp.getString("dbname");
        dbuser = minersProp.getString("dbuser");
        dburl = minersProp.getString("dburl");
        dbpass = minersProp.getString("dbpass");
        dbdriver = minersProp.getString("dbdriver");
        dbjndi = minersProp.getString("dbjndi");
        
        
        administrator = minersSqlCreation.getString("administrator");
        admpass = minersSqlCreation.getString("admpass");
        itemstatuscreation = minersSqlCreation.getString("itemstatuscreation");
        possitioncreation = minersSqlCreation.getString("possitioncreation");
        rolecreation = minersSqlCreation.getString("rolecreation");
        
        Connection con = null;
        Statement s = null;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dbjndi);

            con = ds.getConnection(dbuser, dbpass);

            s = con.createStatement();

            s.execute(itemstatuscreation);
            s.execute(possitioncreation);
            s.execute(rolecreation);
            s.execute(administrator);
            s.execute(admpass);
            
            con.close();

        } catch (NamingException | SQLException e) {
            throw new InternalServerErrorException("Class Not Found", e);
        }

    }

    public InetAddress getIp() {
        try {

            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new InternalServerErrorException("Could not get the host IP", e);
        }
        return ip;
    }

}
