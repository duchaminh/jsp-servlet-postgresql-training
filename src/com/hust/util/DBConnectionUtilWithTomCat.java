package com.hust.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBConnectionUtilWithTomCat {
	private static final String URL = "jdbc:postgresql://localhost/training";
	
	private static final String DRIVER = "org.postgresql.Driver";
	
	private static final String USERNAME = "postgres";
	
	private static final String PASSWORD = "toank21";
	
	private static BasicDataSource ds = new BasicDataSource();
	 
    static {
        ds.setDriverClassName(DRIVER);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        ds.setMaxOpenPreparedStatements(100);
    }
	
    public static Connection openConnection() throws SQLException {
        return ds.getConnection();
    }
}
