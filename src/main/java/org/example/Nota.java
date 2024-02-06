package org.example;

public class Nota {

    public String numeDisciplina;
    public int nota;
    public String date;

    public Nota(String numeDisciplina, int nota, String date) {
        this.numeDisciplina = numeDisciplina;
        this.nota = nota;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Subject: " + numeDisciplina + ", Grade: " + nota + ", Date: " + date;
    }

    public String getNumeDisciplina() {
        return numeDisciplina;
    }

    public int getNota() {
        return nota;
    }

    public String getDate() {
        return date;
    }
}
