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

    public void showDisciplinesWithGrades() {
        // Create a set to store unique disciplines
        Set<String> disciplines = new HashSet<>();

        // Iterate through the list of grades and add discipline names to the set
        for (Nota nota : note) {
            disciplines.add(nota.getNumeDisciplina());
        }

        // Print out the unique discipline names
        System.out.println("Disciplines: ");
        for (String discipline : disciplines) {
            System.out.println(discipline);
        }
    }

    public void sortAllNotesDescending() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparingInt(Nota::getNota).reversed())
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }//gata

    public void sortAllNotesAsceending() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparingInt(Nota::getNota))
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }//gata

    public void sortAllNotesDescendingForADiscipline(String disciplina) {
        // Sorting the note list based on the grade in descending order using streams

        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina)) // Filter notes by discipline
                .sorted(Comparator.comparingInt(Nota::getNota).reversed()) // Sort filtered notes by grade in descending order
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        filteredNotes.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingForADiscipline(String disciplina) {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina)) // Filter notes by discipline
                .sorted(Comparator.comparingInt(Nota::getNota)) // Sort filtered notes by grade in descending order
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        filteredNotes.forEach(System.out::println);
    }

    public void sortAllNotesDescendingByDate() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingByDate() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getDate))
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesDescendingForADisciplineByDate(String disciplina) {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingForADisciplineByDate(String disciplina) {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .sorted(Comparator.comparing(Nota::getDate).reversed())
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesDescendingByDisciplina() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getNumeDisciplina).reversed())
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void sortAllNotesAsceendingByDisciplina() {
        // Sorting the note list based on the grade in descending order using streams
        List<Nota> sortedGrades = note.stream()
                .sorted(Comparator.comparing(Nota::getNumeDisciplina))
                .collect(Collectors.toList());

        // Printing each sorted Nota object
        sortedGrades.forEach(System.out::println);
    }

    public void showGradesForDiscipline(String disciplina) {
        // Filtering the note list based on the discipline using streams
        List<Nota> filteredNotes = note.stream()
                .filter(n -> n.getNumeDisciplina().equals(disciplina))
                .collect(Collectors.toList());

        // Printing each filtered Nota object
        filteredNotes.forEach(System.out::println);
    }

    public void showAvarageForDiscipline(String disciplina) {
        // Filtering the note list based on the discipline using streams
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
        // Create a set to store unique disciplines
        Set<String> disciplines = new HashSet<>();

        // Iterate through the list of grades and add discipline names to the set
        for (Nota nota : note) {
            disciplines.add(nota.getNumeDisciplina());
        }
        for (String discipline : disciplines) {
            showAvarageForDiscipline(discipline);
        }
    }

    private Double avarageForEachDiscipline(String disciplina) {
        // Filtering the note list based on the discipline using streams
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
        // Create a map to store the student's name and their average grade for the given discipline
        Map<String, Double> leaderboard = new HashMap<>();

        // Iterate through the list of students and calculate the average grade for the given discipline
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

        // Sort the leaderboard by average grade in descending order
        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Print out the leaderboard
        System.out.println("Leaderboard for " + disciplina + ":");
        sortedLeaderboard.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    public static void LeaderboardForSubjectForYear(List<Student> students, String disciplina, int an) {
        // Create a map to store the student's name and their average grade for the given discipline
        Map<String, Double> leaderboard = new HashMap<>();

        // Iterate through the list of students and calculate the average grade for the given discipline
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

        // Sort the leaderboard by average grade in descending order
        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Print out the leaderboard
        System.out.println("Leaderboard for " + disciplina + " for year " + an + ":");
        sortedLeaderboard.forEach((k, v) -> System.out.println(k + " - " + v));
    }

    public static void LeaderboardForYear(List<Student> students, int an) {
        // Create a map to store the student's name and their average grade for the given discipline
        Map<String, Double> leaderboard = new HashMap<>();

        // Iterate through the list of students and calculate the average grade for the given discipline
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

        // Sort the leaderboard by average grade in descending order
        Map<String, Double> sortedLeaderboard = leaderboard.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Print out the leaderboard
        System.out.println("Leaderboard for year " + an + ":");
        sortedLeaderboard.forEach((k, v) -> System.out.println(k + " - " + v));
    }

}
