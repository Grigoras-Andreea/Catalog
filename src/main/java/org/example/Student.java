package org.example;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

public class Student {
    //functions that will be used by the student

    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    private int an;
    public List<Nota> note = new ArrayList<Nota>();

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

    public void showInfoAboutStudent() {
        System.out.println(this.nume + " " + this.prenume);
        System.out.println("An: " + this.an);
    }

    public void showAllGrades() {
        note.forEach(System.out::println);
    }

    public void showDisciplinesWithGrades() {

        Set<String> disciplines = new HashSet<>();


        for (Nota nota : note) {
            disciplines.add(nota.getNumeDisciplina());
        }


        System.out.println("Disciplines: ");
        for (String discipline : disciplines) {
            System.out.println(discipline);
        }
    }

    public void sortAllNotesDescending() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparingInt(Nota::getNota).reversed())
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceending() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparingInt(Nota::getNota))
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesDescendingForADiscipline(String disciplina) {


        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina)) // Filter notes by discipline
                .sorted(Comparator.comparingInt(Nota::getNota).reversed()) // Sort filtered notes by grade in descending order
                .collect(Collectors.toList());


        filteredNotes.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingForADiscipline(String disciplina) {

        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina)) // Filter notes by discipline
                .sorted(Comparator.comparingInt(Nota::getNota)) // Sort filtered notes by grade in descending order
                .collect(Collectors.toList());


        filteredNotes.forEach(System.out::println);
    }

    public void sortAllNotesDescendingByDate() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingByDate() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getDate))
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesDescendingForADisciplineByDate(String disciplina) {

        List<Nota> sortedGrades = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingForADisciplineByDate(String disciplina) {

        List<Nota> sortedGrades = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesDescendingByDisciplina() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getNumeDisciplina).reversed())
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingByDisciplina() {

        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getNumeDisciplina))
                .collect(Collectors.toList());


        sortedGrades.forEach(System.out::println);
    }

    public void showGradesForDiscipline(String disciplina) {

        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .collect(Collectors.toList());


        filteredNotes.forEach(System.out::println);
    }

    public void showAvarageForDiscipline(String disciplina) {

        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .collect(Collectors.toList());
        Double sum = (double) 0;
        for (Nota nota : filteredNotes) {
            sum += nota.getNota();
        }
        System.out.println("Avarage for " + disciplina + " is: " + sum / filteredNotes.size());
    }

    public void showAvarageForAllDisciplines() {

        Set<String> disciplines = new HashSet<>();


        for (Nota nota : note) {
            disciplines.add(nota.getNumeDisciplina());
        }
        for (String discipline : disciplines) {
            showAvarageForDiscipline(discipline);
        }
    }

    private Double avarageForEachDiscipline(String disciplina) {

        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .collect(Collectors.toList());
        Double sum = (double) 0;
        for (Nota nota : filteredNotes) {
            sum += nota.getNota();
        }
        return sum / filteredNotes.size();
    }

    public void showAverageOverAllBasedOnDisciplineAvarage() {
        // Create a set to store unique disciplines
        Set<String> disciplines = new HashSet<>();

        // Iterate through the list of grades and add discipline names to the set
        for (Nota nota : note) {
            disciplines.add(nota.getNumeDisciplina());
        }
        Double sum = (double) 0;
        for (String discipline : disciplines) {
            sum += avarageForEachDiscipline(discipline);
        }
        System.out.println("Avarage for all disciplines is: " + sum / disciplines.size());

    }

    public static void LeaderboardForSubject(List<Student> students, String disciplina) {

        Map<String, Double> leaderboard = new HashMap<>();


        for (Student student : students) {
            Double sum = (double) 0;
            int count = 0;
            for (Nota nota : student.note) {
                if (nota.getNumeDisciplina().equals(disciplina)) {
                    sum += nota.getNota();
                    count++;
                }
            }
            if (count != 0) {
                leaderboard.put(student.getNume() + " " + student.getPrenume(), sum / count);
            }
        }


        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        System.out.println("Leaderboard for disciplina " + disciplina + ":");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedLeaderboard.entrySet()) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue());
            rank++;
        }
        System.out.println("\n\n");
        System.out.println("--------------------------------");
    }

    public static void LeaderboardForSubjectForYear(List<Student> students, String disciplina, int an) {

        Map<String, Double> leaderboard = new HashMap<>();


        for (Student student : students) {
            if (student.getAn() == an) {
                Double sum = (double) 0;
                int count = 0;
                for (Nota nota : student.note) {
                    if (nota.getNumeDisciplina().equals(disciplina)) {
                        sum += nota.getNota();
                        count++;
                    }
                }
                if (count != 0) {
                    leaderboard.put(student.getNume() + " " + student.getPrenume(), sum / count);
                }
            }
        }


        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        System.out.println("Leaderboard for year " + an +" and discipline: "+ disciplina + ":");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedLeaderboard.entrySet()) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue());
            rank++;
        }
        System.out.println("\n\n");
        System.out.println("--------------------------------");
    }

    public static void LeaderboardForYear(List<Student> students, int an) {

        Map<String, Double> leaderboard = new HashMap<>();


        for (Student student : students) {
            if (student.getAn() == an) {
                Double sum = (double) 0;
                int count = 0;
                for (Nota nota : student.note) {
                    sum += nota.getNota();
                    count++;
                }
                if (count != 0) {
                    leaderboard.put(student.getNume() + " " + student.getPrenume(), sum / count);
                }
            }
        }


        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        System.out.println("Leaderboard for year " + an + ":");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedLeaderboard.entrySet()) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue());
            rank++;
        }
        System.out.println("\n\n");
        System.out.println("--------------------------------");
    }

}
