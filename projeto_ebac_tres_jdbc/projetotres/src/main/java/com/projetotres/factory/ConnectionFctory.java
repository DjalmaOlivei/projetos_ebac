package com.projetotres.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFctory {

    private static Connection connection = null;
    private static final Dotenv dotenv = Dotenv.configure().directory("src/main/java").load();

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            connection = connectionInit();
        }else if (connection.isClosed()){
            connection = connectionInit();
        }
        return connection;
    }

    public static Connection connectionInit() throws Exception {
            String urlString = getUrl();
            String userString = getUser();
            String passwordString = getPassword();
                connection = DriverManager.getConnection("jdbc:postgresql://"+urlString, userString, passwordString);
        
        return connection;
    }


    private static String getUrl(){
        String url = dotenv.get("URLDBPOSTGRE");
        return url;
    }

    private static String getUser(){
        String user = dotenv.get("USERDBPOSTGRE");
        return user;
    }

    private static String getPassword(){
        String password = dotenv.get("PASSWORDDBPOSTGRE");
        return password;
    }

}