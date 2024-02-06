package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        /*App app = new App();
        app.connect();*/

        // Call the method to insert data from a file into the database
        //app.insertDataFromFile("C:\\Users\\dutua\\OneDrive\\Desktop\\MIP2\\src\\main\\java\\org\\example\\date.txt");
       // app.insertIntoProfesor(1,"Moisescu","Andrei","moisescuandrei","1238");
        //app.insertIntoDisciplina(1,10,1,"MIP");
       // app.insertNota(1,1,10);

        Database db = new Database();
        db.connect();
        Scanner scanner = new Scanner(System.in);


        //MENU

        while(true){
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("Introduceti optiunea: ");
            int optiune = scanner.nextInt();
            if(optiune == 1){
                System.out.println("1. Student");
                System.out.println("2. Profesor");
                System.out.println("Introduceti optiunea: ");
                int optiune2 = scanner.nextInt();
                if(optiune2 == 1){
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti parola: ");
                    String parola = scanner.next();
                    int id = db.studentLogIn(username, parola);
                    if(id == -1){
                        System.out.println("Username sau parola gresita!");
                    }
                    else{
                        System.out.println("Logare cu succes!");
                        //meniu student
                        while(true){
                            System.out.println("1. Afisare note");
                            System.out.println("2. Afisare medie");
                            System.out.println("3. Afisare discipline");
                            System.out.println("4. Exit");
                            System.out.println("Introduceti optiunea: ");
                            int optiune3 = scanner.nextInt();
                            switch(optiune3){
                                case 1:
                                    db.showStudentGrades(id);
                                    break;
                                case 2:
                                    db.showAverage(id);
                                    break;
                                case 3:
                                    db.showDisciplines(id);
                                    break;
                                case 4:
                                    System.out.println("Comanda efectuata cu succes!");
                                    break;

                                default:
                                    System.out.println("Optiune invalida!");
                            }
                             if (optiune3 == 4) {
                                 //iesire din meniu
                                 System.out.println("Iesire din meniul student.");
                                 break;
                             }


                        }
                    }
                }
                else if(optiune2 == 2){
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti parola: ");
                    String parola = scanner.next();
                    int id = db.profesorLogIn(username, parola);
                    if(id == -1){
                        System.out.println("Username sau parola gresita!");
                    }
                    else{
                        System.out.println("Logare cu succes!");
                        //meniu  Profesor
                        while(true)
                        {
                            System.out.println("1. Pune nota stundentilor");
                            System.out.println("2. Afisare medie finala curs");
                            System.out.println("3. Afisare lista studenti");
                            System.out.println("4.Adaugare disciplina");
                            System.out.println("5. Exit");
                            System.out.println("Introduceti optiunea: ");
                            int optiune4 = scanner.nextInt();
                            switch(optiune4){
                                case 1:
                                    db.showProfesorDisciplines(id);
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina = scanner.nextInt();
                                    if(!db.checkIfProfesorTeachesDisciplina(idDisciplina, id)){
                                        System.out.println("Nu predati aceasta disciplina!");
                                        break;
                                    }
                                    db.showStudents();
                                    System.out.println("Introduceti id-ul studentului: ");
                                    int idStudent = scanner.nextInt();
                                    if(!db.checkIfStudentExists(idStudent)){
                                        System.out.println("Studentul nu exista sau nu face parte din aceasta disciplina!");
                                        break;
                                    }
                                    System.out.println("Introduceti nota: ");
                                    int nota = scanner.nextInt();
                                    db.puneNotaStudentilor(idDisciplina, idStudent, nota);

                                    break;
                                case 2:
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina2 = scanner.nextInt();
                                    db.ShowAverageCurs(idDisciplina2);
                                    break;
                                case 3:
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina3 = scanner.nextInt();
                                    db.showStudents(idDisciplina3);
                                    break;

                                case 4:
                                    System.out.println("Introduceti numele disciplinei: ");
                                    String numeDisciplina = scanner.next();
                                    db.insertIntoDisciplina(db.getLastIDFromDisciplina() + 1, 0,id, numeDisciplina);
                                    break;
                                case 5:
                                    System.out.println("Comanda efectuata cu succes!");
                                    break;
                                default:
                                    System.out.println("Optiune invalida!");
                            }
                            if (optiune4 == 5) {
                                //iesire din meniu
                                System.out.println("Iesire din meniul profesor.");
                                break;
                            }
                        }

                    }
                }
                else{
                    System.out.println("Optiune invalida!");
                }
            }
            else if(optiune == 2){
                System.out.println("1. Student");
                System.out.println("2. Profesor");
                System.out.println("3. Exit");
                System.out.println("Introduceti optiunea: ");
                int optiune2 = scanner.nextInt();
                if(optiune2 == 1){
                    int id = db.GetStudentLastID();
                    System.out.println("Introduceti nume: ");
                    String nume = scanner.next();
                    System.out.println("Introduceti prenume: ");
                    String prenume = scanner.next();
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    if(db.checkIfUsernameExists(username)){
                        System.out.println("Username-ul exista deja!");
                    }
                    else{
                        System.out.println("Introduceti parola: ");
                        String parola = scanner.next();
                        System.out.println("Introduceti anul: ");
                        int an = scanner.nextInt();
                        db.insertIntoStudent(id + 1, nume, prenume, an, username, parola);
                        System.out.println("Inregistrare cu succes!");

                    }


                }
                else if(optiune2 == 2){
                    int id = db.GetProfesorLastID();
                    System.out.println("Introduceti nume: ");
                    String nume = scanner.next();
                    System.out.println("Introduceti prenume: ");
                    String prenume = scanner.next();
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    if(db.checkIfUsernameExists(username)){
                        System.out.println("Username-ul exista deja!");
                    }
                    else{
                        System.out.println("Introduceti parola: ");
                        String parola = scanner.next();
                        db.insertIntoProfesor(id + 1, nume, prenume, username, parola);
                        System.out.println("Inregistrare cu succes!");
                    }
                }
                else if(optiune2 == 3){
                    System.out.println("Comanda efectuata cu succes!");
                }
                else{
                    System.out.println("Optiune invalida!");
                }
            }
            else if(optiune == 3){
                break;
            }
            else{
                System.out.println("Optiune invalida!");
            }
        }
    }
}
