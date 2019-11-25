package com.hust.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DBConnectionUtil {
private static final String URL = "jdbc:postgresql://localhost/training";
	
	private static final String DRIVER = "org.postgresql.Driver";
	
	private static final String USERNAME = "postgres";
	
	private static final String PASSWORD = "toank21";
	
	private static Connection connection = null;
	
	public static Connection openConnection() throws SQLException {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }
}
