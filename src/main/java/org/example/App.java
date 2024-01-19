package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App
{

    final String url = "jdbc:postgresql://localhost:5432/ProiectMIP" ;
    final String user = "postgres";
    final String password = "1q2w3e";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public void insertIntoStudent(int id, String Nume, String Prenume, int an, String username, String parola){
        String sql = "INSERT INTO Student(id, nume, prenume, an, username, parola) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, Nume);  // Use setString for a regular string column
            pstmt.setString(3, Prenume);
            pstmt.setInt(4, an);
            pstmt.setString(5, username);
            pstmt.setString(6, parola);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void main( String[] args ) throws SQLException {
        App app = new App();
        app.connect();
        app.insertIntoStudent(1, "Popescu", "Ion", 2, "popescuion", "1234");
    }
}