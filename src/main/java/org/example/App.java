package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class App
{
    public static void main( String[] args ) throws SQLException {
        Database app = new Database();
        app.connect();
        app.insertIntoStudent(1, "Popescu", "Ion", 2, "popescuion", "1234");
    }
}