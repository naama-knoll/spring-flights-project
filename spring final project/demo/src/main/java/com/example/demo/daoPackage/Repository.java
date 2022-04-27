package com.example.demo.daoPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
    private Connection connection=null;
    private Statement statement=null;

    //make the connection to the db
    public Connection getConnection(String url){
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(
                    "jdbc:postgresql:"+url,
                    "postgres",
                    "Naama2745");
            System.out.println("database connected");
        } catch (Exception e) {
            System.out.println("error!!!!");
        }
        return this.connection;
    }

    //create statement
    public Statement getStatement(){
        try {
            statement=this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

}

