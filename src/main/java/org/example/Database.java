package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Database {
    final String url = "jdbc:postgresql://localhost:5432/NoteCatalog" ;
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
    public void setLoggedInProfessorId(int professorId) {
        this.profesorIDLogged = professorId;
    }

    private int profesorIDLogged;

    public boolean checkIfUsernameExistsForStudent(String username) {
        String sql = "SELECT * FROM \"Student\" WHERE \"Username\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Username already exists
                    return true;
                } else {
                    // Username does not exist
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean checkIfUsernameExistsForProfesor(String username) {
        String sql = "SELECT * FROM \"Profesor\" WHERE \"Username\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Username already exists
                    return true;
                } else {
                    // Username does not exist
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean checkIfUsernameExists(String username) {
        return checkIfUsernameExistsForStudent(username) || checkIfUsernameExistsForProfesor(username);
    }


    public void insertIntoStudent(int id, String nume, String Prenume, int an, String username, String parola){
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

    private int getNotaLastID(){
        String sql = "SELECT * FROM \"Note\" ORDER BY \"ID_Nota\" DESC LIMIT 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_Nota");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void insertNota(int idStudent, int ID_Disciplina, int nota) {
        String sql = "INSERT INTO \"Note\"(\"ID_Nota\",\"ID_Disciplina\",\"ID_Student\", \"Data\",\"Nota\") VALUES(?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, getNotaLastID()+1);
            pstmt.setInt(2, ID_Disciplina);
            pstmt.setInt(3, idStudent);
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(5, nota);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getLastIDFromDisciplina(){
        String sql = "SELECT * FROM \"Disciplina\" ORDER BY \"ID_Disciplina\" DESC LIMIT 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_Disciplina");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
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

    public int studentLogIn(String username, String parola) {
        String sql = "SELECT * FROM \"Student\" WHERE \"Username\" = ? AND \"Parola\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, parola);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Login successful, return the student ID
                    return rs.getInt("ID_Student");
                } else {
                    // Login failed
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int profesorLogIn(String username, String parola) {
        String sql = "SELECT * FROM \"Profesor\" WHERE \"Username\" = ? AND \"Parola\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, parola);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Login successful, return the profesor ID
                    int profesorId = rs.getInt("ID_Profesor");
                    return rs.getInt("ID_Profesor");
                } else {
                    // Login failed
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int GetStudentLastID(){
        String sql = "SELECT * FROM \"Student\" ORDER BY \"ID_Student\" DESC LIMIT 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return rs.getInt("ID_Student");
                } else {

                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int GetProfesorLastID(){
        String sql = "SELECT * FROM \"Profesor\" ORDER BY \"ID_Profesor\" DESC LIMIT 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return rs.getInt("ID_Profesor");
                } else {

                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    public void showStudentGrades(int studentId) {
        String sql = "SELECT D.\"Nume_disciplina\" AS Subject, N.\"Nota\" AS Grade, N.\"Data\" AS Date " +
                "FROM \"Note\" N " +
                "JOIN \"Disciplina\" D ON N.\"ID_Disciplina\" = D.\"ID_Disciplina\" " +
                "WHERE N.\"ID_Student\" = ?";


        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String subject = rs.getString("Subject");
                    int grade = rs.getInt("Grade");
                    Timestamp date = rs.getTimestamp("Date");

                    System.out.println("Subject: " + subject + ", Grade: " + grade + ", Date: " + date);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showAverage(int studentId) {
        String sql = "SELECT AVG(\"Nota\") AS Average " +
                "FROM \"Note\" " +
                "WHERE \"ID_Student\" = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double average = rs.getDouble("Average");

                    System.out.println("Media: " + average);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showDisciplines(int studentId) {
        String sql = "SELECT D.\"Nume_disciplina\" AS Subject " +
                "FROM \"Note\" N " +
                "JOIN \"Disciplina\" D ON N.\"ID_Disciplina\" = D.\"ID_Disciplina\" " +
                "WHERE N.\"ID_Student\" = ? " +
                "GROUP BY D.\"Nume_disciplina\"";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String subject = rs.getString("Subject");

                    System.out.println("Subject: " + subject);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean checkIfProfesorCanGradeDisciplina(int idDisciplina) {
        String sql = "SELECT * FROM \"Disciplina\" WHERE \"ID_Disciplina\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDisciplina);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Disciplina exists
                    int idProfesor = rs.getInt("ID_Profesor");
                    if (idProfesor == profesorIDLogged) {
                        // Profesorul poate sa puna nota la disciplina respectiva
                        return true;
                    } else {
                        // Profesorul nu poate sa puna nota la disciplina respectiva
                        return false;
                    }
                } else {
                    // Disciplina does not exist
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void puneNotaStudentilor(int idDisciplina, int idStudent, int nota) {
        // Verifica daca profesorul respectiv poate sa puna nota la disciplina respectiva
        if (checkIfProfesorCanGradeDisciplina(idDisciplina)) {
            // Verifica daca studentul exista
            if (checkIfStudentExists(idStudent)) {
                // Insereaza nota in baza de date
                insertNota(idStudent, idDisciplina, nota);
                System.out.println("Nota a fost pusa cu succes!");
            } else {
                System.out.println("Studentul nu exista!");
            }
        } else {
            // Profesorul nu poate sa puna nota
            System.out.println("Profesorul nu poate sa puna nota la aceasta disciplina!");
        }
    }
    boolean checkIfStudentExists(int idStudent) {
        String sql = "SELECT * FROM \"Student\" WHERE \"ID_Student\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idStudent);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void ShowAverageCurs(int idDisciplina) {
        String sql = "SELECT AVG(\"Nota\") AS Average " +
                "FROM \"Note\" " +
                "WHERE \"ID_Disciplina\" = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDisciplina);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double average = rs.getDouble("Average");

                    System.out.println("Media: " + average);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void showStudents(int idDisciplina) {
        String sql = "SELECT S.\"Nume\" AS Nume, S.\"Prenume\" AS Prenume " +
                "FROM \"Note\" N " +
                "JOIN \"Student\" S ON N.\"ID_Student\" = S.\"ID_Student\" " +
                "WHERE N.\"ID_Disciplina\" = ? " +
                "GROUP BY S.\"Nume\", S.\"Prenume\"";
        List<String> studentList = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDisciplina);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nume = rs.getString("Nume");
                    String prenume = rs.getString("Prenume");

                    System.out.println( nume + " " + prenume);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Afisarea studentilor
        System.out.println("Studenti:");
        studentList.forEach(System.out::println);

        // Verificare daca utilizatorul doreste sortare
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doriti sa sortati alfabetic? (Da/Nu)");
        String optiuneSortare = scanner.nextLine();

        if ("Da".equalsIgnoreCase(optiuneSortare)) {
            // Utilizare stream pentru sortare alfabetica
            List<String> studentiSortati = studentList.stream()
                    .sorted()
                    .collect(Collectors.toList());

            // Afisare studenti sortati
            System.out.println("Studenti sortati alfabetic:");
            studentiSortati.forEach(System.out::println);
        } else {
            // Continuare cu meniul sau alte operatii
            System.out.println("Continuare cu meniul sau alte operatii...");
        }
    }

    public void showProfesorDisciplines(int profesorId) {
        String sql = "SELECT \"ID_Disciplina\", \"Nume_disciplina\" FROM \"Disciplina\" WHERE \"ID_Profesor\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, profesorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idDisciplina = rs.getInt("ID_Disciplina");
                    String disciplina = rs.getString("Nume_disciplina");
                    System.out.println("ID: " + idDisciplina + ", Disciplina: " + disciplina);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkIfProfesorTeachesDisciplina(int idDisciplina, int id) {
        String sql = "SELECT * FROM \"Disciplina\" WHERE \"ID_Disciplina\" = ? AND \"ID_Profesor\" = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDisciplina);
            pstmt.setInt(2, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void showStudents() {
        String sql = "SELECT * FROM \"Student\"";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_Student");
                    String nume = rs.getString("Nume");
                    String prenume = rs.getString("Prenume");

                    System.out.println("ID: " + id + ", Nume: " + nume + ", Prenume: " + prenume);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}


