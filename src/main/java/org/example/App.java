package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.connect();
        Scanner scanner = new Scanner(System.in);

        List<Student> students = db.getAllStudents();

        int profesorIDLogged = -1;

        // MENU
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("Introduceti optiunea: ");
            int optiune = scanner.nextInt();

            if (optiune == 1) {
                System.out.println("1. Student");
                System.out.println("2. Profesor");
                System.out.println("Introduceti optiunea: ");
                int optiune2 = scanner.nextInt();

                if (optiune2 == 1) {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti parola: ");
                    String parola = scanner.next();
                    int id = db.studentLogIn(username, parola);

                    if (id == -1) {
                        System.out.println("Username sau parola gresita!");
                    } else {
                        System.out.println("Logare cu succes!");
                        // meniu student
                        while (true) {
                            Student student = db.sendStudentInfo(id);
                            System.out.println("1. Afisare informatii student");
                            System.out.println("2. Afisare note");
                            System.out.println("3. Afisare medii");
                            System.out.println("4. Afisare clasament");
                            System.out.println("5. Schimbare username");
                            System.out.println("6. Schimbare parola");
                            System.out.println("7. Exit");
                            System.out.println("Introduceti optiunea: ");
                            int optiune3 = scanner.nextInt();

                            switch (optiune3) {
                                case 1:
                                    student.showInfoAboutStudent();
                                    break;
                                case 2:
                                    System.out.println("1.Afisare note pentru o disciplina");
                                    System.out.println("2.Afisare toate notele");
                                    System.out.println("3.Inapoi");
                                    System.out.println("Introduceti optiunea: ");
                                    int optiune4 = scanner.nextInt();
                                    switch (optiune4){
                                        case 1:
                                            System.out.println("1.Afisare note nesortate");
                                            System.out.println("2.Afisare note sortate dupa data");
                                            System.out.println("3.Afisare note sortate dupa nota");
                                            System.out.println("4.Inapoi");
                                            System.out.println("Introduceti optiunea: ");
                                            int optiune5 = scanner.nextInt();
                                            String numeDisciplina;
                                            switch (optiune5){
                                                case 1:
                                                    student.showDisciplinesWithGrades();
                                                    System.out.println("Introduceti numele disciplinei disciplinei: ");
                                                    numeDisciplina = scanner.next();
                                                    student.showGradesForDiscipline(numeDisciplina);
                                                    break;
                                                case 2:
                                                    System.out.println("1.Sortare crescatoare");
                                                    System.out.println("2.Sortare descrescatoare");
                                                    System.out.println("3.Inapoi");
                                                    System.out.println("Introduceti optiunea: ");
                                                    int optiune6 = scanner.nextInt();
                                                    switch (optiune6){
                                                        case 1:
                                                            student.showDisciplinesWithGrades();
                                                            System.out.println("Introduceti numele disciplinei disciplinei: ");
                                                            numeDisciplina = scanner.next();
                                                            student.sortAllNotesAsceendingForADisciplineByDate(numeDisciplina);
                                                            break;
                                                        case 2:
                                                            student.showDisciplinesWithGrades();
                                                            System.out.println("Introduceti numele disciplinei disciplinei: ");
                                                            numeDisciplina = scanner.next();
                                                            student.sortAllNotesDescendingForADisciplineByDate(numeDisciplina);
                                                            break;
                                                        case 3:
                                                            break;
                                                        default:
                                                            System.out.println("Optiune invalida!");
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("1.Sortare crescatoare");
                                                    System.out.println("2.Sortare descrescatoare");
                                                    System.out.println("3.Inapoi");
                                                    System.out.println("Introduceti optiunea: ");
                                                    int optiune7 = scanner.nextInt();
                                                    switch (optiune7){
                                                        case 1:
                                                            student.showDisciplinesWithGrades();
                                                            System.out.println("Introduceti numele disciplinei disciplinei: ");
                                                            numeDisciplina = scanner.next();
                                                            student.sortAllNotesAsceendingForADiscipline(numeDisciplina);
                                                            break;
                                                        case 2:
                                                            student.showDisciplinesWithGrades();
                                                            System.out.println("Introduceti numele disciplinei disciplinei: ");
                                                            numeDisciplina = scanner.next();
                                                            student.sortAllNotesDescendingForADiscipline(numeDisciplina);
                                                            break;
                                                        case 3:
                                                            break;
                                                        default:
                                                            System.out.println("Optiune invalida!");
                                                    }
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Optiune invalida!");
                                            }
                                            break;
                                        case 2:
                                            System.out.println("1.Afisare note nesortate");
                                            System.out.println("2.Afisare note sortate dupa data");
                                            System.out.println("3.Afisare note sortate dupa nota");
                                            System.out.println("4.Inapoi");
                                            System.out.println("Introduceti optiunea: ");
                                            int optiune8 = scanner.nextInt();
                                            String numeDisciplina2;
                                            switch (optiune8){
                                                case 1:
                                                    student.showAllGrades();
                                                    break;
                                                case 2:
                                                    System.out.println("1.Sortare crescatoare");
                                                    System.out.println("2.Sortare descrescatoare");
                                                    System.out.println("3.Inapoi");
                                                    System.out.println("Introduceti optiunea: ");
                                                    int optiune6 = scanner.nextInt();
                                                    switch (optiune6){
                                                        case 1:
                                                            student.sortAllNotesAsceendingByDate();
                                                            break;
                                                        case 2:
                                                            student.sortAllNotesDescendingByDate();
                                                            break;
                                                        case 3:
                                                            break;
                                                        default:
                                                            System.out.println("Optiune invalida!");
                                                    }
                                                case 3:
                                                    System.out.println("1.Sortare crescatoare");
                                                    System.out.println("2.Sortare descrescatoare");
                                                    System.out.println("3.Inapoi");
                                                    System.out.println("Introduceti optiunea: ");
                                                    int optiune7 = scanner.nextInt();
                                                    switch (optiune7){
                                                        case 1:
                                                            student.sortAllNotesAsceending();
                                                            break;
                                                        case 2:
                                                            student.sortAllNotesDescending();
                                                            break;
                                                        case 3:
                                                            break;
                                                        default:
                                                            System.out.println("Optiune invalida!");
                                                    }
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Optiune invalida!");
                                            }
                                            break;
                                        case 3:
                                            break;
                                        default:
                                            System.out.println("Optiune invalida!");
                                    }

                                case 3:
                                    System.out.println("1.Afisare medii la fiecare disciplina disciplina");
                                    System.out.println("2.Afisare medie generala");
                                    System.out.println("3.Inapoi");
                                    System.out.println("Introduceti optiunea: ");
                                    int optiune9 = scanner.nextInt();
                                    switch(optiune9){
                                        case 1:
                                            student.showAvarageForAllDisciplines();
                                            break;
                                        case 2:
                                            student.showAverageOverAllBasedOnDisciplineAvarage();
                                            break;
                                        case 3:
                                            break;
                                        default:
                                            System.out.println("Optiune invalida!");
                                    }
                                    break;
                                case 4:
                                    System.out.println("1.Clasament pe an");
                                    System.out.println("2.Clasament pe an si disciplina");
                                    System.out.println("3.Inapoi");
                                    System.out.println("Introduceti optiunea: ");
                                    int optiune10 = scanner.nextInt();
                                    switch(optiune10){
                                        case 1:
                                            System.out.println("Introduceti anul: ");
                                            int an = scanner.nextInt();
                                            Student.LeaderboardForYear(students, an);
                                            break;
                                        case 2:
                                            System.out.println("Introduceti anul: ");
                                            int an2 = scanner.nextInt();
                                            db.showAllDisciplines();
                                            System.out.println("Introduceti numele disciplinei: ");
                                            String numeDisciplina = scanner.next();
                                            Student.LeaderboardForSubjectForYear(students, numeDisciplina, an2);
                                            break;
                                        case 3:
                                            break;
                                        default:
                                            System.out.println("Optiune invalida!");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Introduceti noul username: ");
                                    String newUsername = scanner.next();
                                    if (db.checkIfUsernameExists(newUsername)) {
                                        System.out.println("Username-ul exista deja!");
                                    } else {
                                        db.changeUsernameStudent(id, newUsername);
                                        System.out.println("Username schimbat cu succes!");
                                    }
                                    break;
                                case 6:
                                    System.out.println("Introduceti noua parola: ");
                                    String newParola = scanner.next();
                                    db.changePasswordStudent(id, newParola);
                                    System.out.println("Parola schimbata cu succes!");
                                    break;
                                case 7:
                                    System.out.println("Comanda efectuata cu succes!");
                                    break;
                                default:
                                    System.out.println("Optiune invalida!");
                            }

                            if (optiune3 == 7) {
                                // iesire din meniu
                                System.out.println("Iesire din meniul student.");
                                break;
                            }
                        }
                    }
                } else if (optiune2 == 2) {
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();
                    System.out.println("Introduceti parola: ");
                    String parola = scanner.next();
                    profesorIDLogged = db.profesorLogIn(username, parola);

                    if (profesorIDLogged == -1) {
                        System.out.println("Username sau parola gresita!");
                    } else {
                        System.out.println("Logare cu succes!");
                        // meniu Profesor
                        while (true) {
                            System.out.println("1. Pune nota studentilor");
                            System.out.println("2. Afisare medie finala curs");
                            System.out.println("3. Afisare lista studenti");
                            System.out.println("4. Adaugare disciplina");
                            System.out.println("5. Modificare/Stergere nota student");
                            System.out.println("6. Exit");
                            System.out.println("Introduceti optiunea: ");
                            int optiune4 = scanner.nextInt();

                            switch (optiune4) {
                                case 1:
                                    db.showProfesorDisciplines(profesorIDLogged);
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina = scanner.nextInt();

                                    if (!db.checkIfProfesorTeachesDisciplina(idDisciplina, profesorIDLogged)) {
                                        System.out.println("Nu predati aceasta disciplina!");
                                        break;
                                    }

                                    db.showStudents();
                                    System.out.println("Introduceti id-ul studentului: ");
                                    int idStudent = scanner.nextInt();

                                    if (!db.checkIfStudentExists(idStudent)) {
                                        System.out.println("Studentul nu exista sau nu face parte din aceasta disciplina!");
                                        break;
                                    }

                                    System.out.println("Introduceti nota: ");
                                    int nota = scanner.nextInt();
                                    db.puneNotaStudentilor(idDisciplina,idStudent,nota,profesorIDLogged);
                                    break;

                                case 2:
                                    db.showProfesorDisciplines(profesorIDLogged);
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina2 = scanner.nextInt();
                                    db.ShowAverageCurs(idDisciplina2);
                                    break;

                                case 3:
                                    db.showProfesorDisciplines(profesorIDLogged);
                                    System.out.println("Introduceti id-ul disciplinei: ");
                                    int idDisciplina3 = scanner.nextInt();
                                    db.showStudents(idDisciplina3);
                                    break;

                                case 4:
                                    System.out.println("Introduceti numele disciplinei: ");
                                    String numeDisciplina = scanner.next();
                                    db.insertIntoDisciplina(db.getLastIDFromDisciplina() + 1, 0, profesorIDLogged, numeDisciplina);
                                    break;
                                case 5:
                                    System.out.println("A. Modificare nota");
                                    System.out.println("B. Stergere nota");
                                    System.out.println("Introduceti optiunea: ");
                                    String optiune5 = scanner.next();
                                    switch (optiune5){
                                        case "A":
                                            //modificare nota
                                            db.showProfesorDisciplines(profesorIDLogged);
                                            System.out.println("Introduceti id-ul disciplinei: ");
                                            int idDisciplina5 = scanner.nextInt();
                                            if (!db.checkIfProfesorTeachesDisciplina(idDisciplina5, profesorIDLogged)) {
                                                System.out.println("Nu predati aceasta disciplina!");
                                                break;
                                            }
                                            db.showStudents(idDisciplina5);
                                            System.out.println("Introduceti id-ul studentului: ");
                                            int idStudent5 = scanner.nextInt();
                                            //verificare daca studentul exista
                                            if (!db.checkIfStudentExists(idStudent5)) {
                                                System.out.println("Studentul nu exista sau nu face parte din aceasta disciplina!");
                                                break;
                                            }
                                            db.showStudentGrades(idStudent5);
                                            System.out.println("Introduceti id-ul notei: ");
                                            int idNota5 = scanner.nextInt();
                                            System.out.println("Introduceti noua nota: ");
                                            int nota5 = scanner.nextInt();
                                            db.modificareNota(idStudent5,idDisciplina5,idNota5,nota5);
                                            System.out.println("Nota a fost modificata cu succes!");
                                            break;
                                        case "B":
                                            // Sterge nota
                                            System.out.println("Stergere nota:");
                                            db.showProfesorDisciplines(profesorIDLogged);
                                            System.out.println("Introduceti id-ul disciplinei: ");
                                            int idDisciplina5B = scanner.nextInt();

                                            if (!db.checkIfProfesorTeachesDisciplina(idDisciplina5B, profesorIDLogged)) {
                                                System.out.println("Nu predati aceasta disciplina!");
                                                break;
                                            }

                                            db.showStudents();
                                            System.out.println("Introduceti id-ul studentului: ");
                                            int idStudent5B = scanner.nextInt();

                                            if (!db.checkIfStudentExists(idStudent5B)) {
                                                System.out.println("Studentul nu exista sau nu face parte din aceasta disciplina!");
                                                break;
                                            }
                                            System.out.println("Notele studentului:");
                                            db.showStudentGrades(idStudent5B);
                                            System.out.println("Introduceti id-ul notei: ");
                                            int idNota5B = scanner.nextInt();

                                            // Sterge nota
                                            db.stergeNota(idStudent5B,idDisciplina5B,idNota5B);

                                            break;

                                        default:
                                            System.out.println("Optiune invalida!");
                                            break;
                                    }
                                    break;

                                case 6:
                                    System.out.println("Comanda efectuata cu succes!");
                                    break;

                                default:
                                    System.out.println("Optiune invalida!");
                            }

                            if (optiune4 == 6) {
                                // iesire din meniu
                                System.out.println("Iesire din meniul profesor.");
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Optiune invalida!");
                }
            } else if (optiune == 2) {
                System.out.println("1. Student");
                System.out.println("2. Profesor");
                System.out.println("3. Exit");
                System.out.println("Introduceti optiunea: ");
                int optiune2 = scanner.nextInt();

                if (optiune2 == 1) {
                    int id = db.GetStudentLastID();
                    System.out.println("Introduceti nume: ");
                    String nume = scanner.next();
                    System.out.println("Introduceti prenume: ");
                    String prenume = scanner.next();
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (db.checkIfUsernameExists(username)) {
                        System.out.println("Username-ul exista deja!");
                    } else {
                        System.out.println("Introduceti parola: ");
                        String parola = scanner.next();
                        System.out.println("Introduceti anul: ");
                        int an = scanner.nextInt();
                        db.insertIntoStudent(id + 1, nume, prenume, an, username, parola);
                        System.out.println("Inregistrare cu succes!");
                    }

                } else if (optiune2 == 2) {
                    int id = db.GetProfesorLastID();
                    System.out.println("Introduceti nume: ");
                    String nume = scanner.next();
                    System.out.println("Introduceti prenume: ");
                    String prenume = scanner.next();
                    System.out.println("Introduceti username: ");
                    String username = scanner.next();

                    if (db.checkIfUsernameExists(username)) {
                        System.out.println("Username-ul exista deja!");
                    } else {
                        System.out.println("Introduceti parola: ");
                        String parola = scanner.next();
                        db.insertIntoProfesor(id + 1, nume, prenume, username, parola);
                        System.out.println("Inregistrare cu succes!");
                    }
                } else if (optiune2 == 3) {
                    System.out.println("Comanda efectuata cu succes!");
                } else {
                    System.out.println("Optiune invalida!");
                }
            } else if (optiune == 3) {
                break;
            } else {
                System.out.println("Optiune invalida!");
            }
        }
    }
}
