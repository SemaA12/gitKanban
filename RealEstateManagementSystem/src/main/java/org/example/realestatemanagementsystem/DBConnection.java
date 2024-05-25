package org.example.realestatemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection dbConnection(){

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/real_estates", "root", "Sizene86!");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
