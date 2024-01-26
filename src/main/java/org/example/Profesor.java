package org.example;

import java.util.ArrayList;

public class Profesor {
    //functions that will be used by the professor
    //de fiecare data cand un profesor face modificari in tabela nota, media din tabela disciplina trebuie recalculata
    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private ArrayList<String> materii; //materiile pe care profesorul le preda

    public Profesor(int id, String nume, String prenume, String username, String parola){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
    }

    public void showDisciplines(){
        System.out.println("Materiile pe care le predati sunt: ");
        for(String materie : materii){
            System.out.println(materie);
        }
    }

    public void showStudents(){
        //afiseaza toti studentii + id-ul lor pt a putea fi selectati la introducerea unei note
    }
}
