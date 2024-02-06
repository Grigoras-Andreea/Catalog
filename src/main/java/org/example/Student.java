package org.example;

import java.sql.Connection;
import java.util.ArrayList;

public class Student {
    //functions that will be used by the student

    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private int an;
    public ArrayList<Nota> note = new ArrayList<Nota>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void addNota(String numeDisciplina, int nota, String date) {
        Nota notaNoua = new Nota(numeDisciplina, nota, date);
        note.add(notaNoua);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id).append("\n");
        sb.append("Name: ").append(nume).append(" ").append(prenume).append("\n");
        sb.append("Username: ").append(username).append("\n");
        sb.append("An: ").append(an).append("\n");
        sb.append("Grades:\n");
        for (Nota nota : note) {
            sb.append(nota).append("\n");
        }
        return sb.toString();
    }
}
