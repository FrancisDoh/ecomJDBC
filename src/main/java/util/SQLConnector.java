package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    // SQL static Connection method that can be called anywhere in the program.
    public static Connection createConnection(){
        //
        Connection DB_connection = null;
        //Connection con = null;
        //final String DB_URL = "jdbc:mysql://localhost:3306/highschool";
        final String DB_URL = "jdbc:mysql://localhost:8889/ecomDB";
        final String DB_USERNAME = "root";
        //final String DB_PASSWORD = "admin";
        final String DB_PASSWORD = "root";
        //
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DB_connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return  DB_connection;

    }
}