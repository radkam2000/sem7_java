import CustomClasses.Odczyt_c;
import CustomClasses.Zapis_c;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> subjectsArray = new ArrayList<>();
        Float[] grades = {2F, 2.5F, 3F, 3.5F, 4F, 4.5F, 5F};
        Odczyt_c.DoTablicy("przedmioty.txt", subjectsArray);

        boolean isExist = subjectsArray.stream().anyMatch(element -> element.toLowerCase().contains("zaaw"));
        System.out.println("Lista przedmiotów zawiera przedmiot z fragmentem \"zaaw\": " + isExist);

        HashMap<String, Float> gradesForSubjectsWithNamesMap = subjectsArray.stream().collect(Collectors.toMap(k -> k, v -> grades[(int) Math.floor(Math.random() * grades.length)], (prev, next) -> next, HashMap::new));
        Stream<Map.Entry<String, Float>> sorted = gradesForSubjectsWithNamesMap.entrySet().stream().sorted(HashMap.Entry.<String, Float>comparingByValue());
        Zapis_c.ZStreamu("Oceny_rosnaco", sorted);

        HashMap<Float, Float> gradesRepetition = new HashMap<>();
        gradesForSubjectsWithNamesMap.forEach((k, v) -> {
            gradesRepetition.merge(v, 1F, (a, b) -> a + b);
        });
        Stream<Map.Entry<Float, Float>> sortedRepetition = gradesRepetition.entrySet().stream().sorted(HashMap.Entry.<Float, Float>comparingByValue());
        ArrayList<Stream<Map.Entry<String, Float>>> streamsWithGrades = new ArrayList<>();

        sortedRepetition.forEach((elem) -> {
            streamsWithGrades.add(gradesForSubjectsWithNamesMap.entrySet().stream().filter((el) -> el.getValue() == elem.getKey()));
        });
        streamsWithGrades.forEach(stream -> {
            Zapis_c.ZStreamu("Oceny_rosnaco_w_zaleznosci_od_ilosci", stream);
        });

        HashMap<String, Float> gradesFromFile = new HashMap<>();
        Odczyt_c.DoMapy("Oceny_rosnaco", gradesFromFile);
        Float gradeSumSem6 = 0.0F;
        Float howManySem6 = 0.0F;
        Float gradeSumSem7 = 0.0F;
        Float howManySem7 = 0.0F;

        for (Map.Entry<String, Float> entry : gradesFromFile.entrySet()) {
            if (entry.getKey().startsWith("6")) {
                gradeSumSem6 += entry.getValue();
                howManySem6++;
            } else if (entry.getKey().startsWith("7")) {
                gradeSumSem7 += entry.getValue();
                howManySem7++;
            }
        }

        System.out.println("Srednia ocen za sem6 : " + gradeSumSem6 / howManySem6);
        System.out.println("Srednia ocen za sem7 : " + gradeSumSem7 / howManySem7);
        System.out.println("Najwyzsza ocena: " + Collections.max(gradesFromFile.entrySet(), Map.Entry.comparingByValue()).getKey());
        System.out.println("Najnizsza ocena: " + Collections.min(gradesFromFile.entrySet(), Map.Entry.comparingByValue()).getKey());

    }
}
