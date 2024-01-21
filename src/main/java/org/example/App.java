package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class App {

    final String url = "jdbc:postgresql://localhost:5432/ProiectMIP";
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

    public void insertIntoStudent(int id, String nume, String Prenume, int an, String username, String parola) {
        String sql = "INSERT INTO \"Student\"(\"ID_Student\", \"Nume\", \"Prenume\", \"An_Studii\", \"Username\", \"Parola\") VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nume);
            pstmt.setString(3, Prenume);
            pstmt.setInt(4, an);
            pstmt.setString(5, username);
            pstmt.setString(6, parola);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertIntoProfesor(int id, String nume, String Prenume, String username, String parola){
        String sql = "INSERT INTO \"Profesor\"(\"ID_Profesor\", \"Nume\", \"Prenume\", \"Username\", \"Parola\") VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nume);
            pstmt.setString(3, Prenume);
            pstmt.setString(4, username);
            pstmt.setString(5, parola);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertIntoDisciplina(int ID_Disciplina, double medie, int idProfesor, String numeDisciplina){
        String sql = "INSERT INTO \"Disciplina\"(\"ID_Disciplina\",\"Medie\",\"ID_Profesor\",\"Nume_disciplina\") VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_Disciplina);
            pstmt.setDouble(2, medie);
            pstmt.setInt(3, idProfesor);
            pstmt.setString(4, numeDisciplina);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming data in the file is comma-separated
                String[] data = line.split(",");
                // Assuming the data structure is: id, nume, prenume, an, username, parola
                int id = Integer.parseInt(data[0]);
                String nume = data[1];
                String prenume = data[2];
                int an = Integer.parseInt(data[3]);
                String username = data[4];
                String parola = data[5];

                // Call the existing insertIntoStudent method to insert data into the database
                insertIntoStudent(id, nume, prenume, an, username, parola);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertNota(int idStudent, int ID_Disciplina, int nota) {
        String sql = "INSERT INTO \"Note\"(\"ID_Disciplina\",\"ID_Student\", \"Data\",\"Nota\") VALUES(?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID_Disciplina);
            pstmt.setInt(2, idStudent);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(4, nota);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        App app = new App();
        app.connect();

        // Call the method to insert data from a file into the database
        //app.insertDataFromFile("C:\\Users\\dutua\\OneDrive\\Desktop\\MIP2\\src\\main\\java\\org\\example\\date.txt");
       // app.insertIntoProfesor(1,"Moisescu","Andrei","moisescuandrei","1238");
        //app.insertIntoDisciplina(1,10,1,"MIP");
       // app.insertNota(1,1,10);
    }
}
