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
        Integer[] grades = {2, 3, 4, 5};
        Odczyt_c.DoTablicy("przedmioty.txt", subjectsArray);

        boolean isExist = subjectsArray.stream().anyMatch(element -> element.toLowerCase().contains("zaaw"));
        System.out.println("Lista przedmiotów zawiera przedmiot z fragmentem \"zaaw\": " + isExist);

        HashMap<String, Integer> gradesForSubjectsWithNamesMap = subjectsArray.stream()
                .collect(Collectors.toMap(k -> k, v -> grades[(int) Math.floor(Math.random() * grades.length)], (prev, next) -> next, HashMap::new));
        Stream<Map.Entry<String, Integer>> sorted = gradesForSubjectsWithNamesMap.entrySet().stream().sorted(HashMap.Entry.<String, Integer>comparingByValue());
        Zapis_c.ZStreamu("Oceny_rosnaco", sorted);

        HashMap<Integer, Integer> gradesRepetition = new HashMap<>();
        gradesForSubjectsWithNamesMap.forEach((k, v) -> {
            gradesRepetition.merge(v, 1, (a, b) -> a + b);
        });
        Stream<Map.Entry<Integer, Integer>> sortedRepetition = gradesRepetition.entrySet().stream().sorted(HashMap.Entry.<Integer, Integer>comparingByValue());
        ArrayList<Stream<Map.Entry<String, Integer>>> streamsWithGrades = new ArrayList<>();

        sortedRepetition.forEach((elem)->{
            System.out.println(elem);
            streamsWithGrades.add(gradesForSubjectsWithNamesMap.entrySet().stream().filter((el)-> el.getValue() == elem.getKey()));
        });
        streamsWithGrades.forEach(stream -> {
            Zapis_c.ZStreamu("Oceny_rosnaco_w_zaleznosci_od_ilosci",stream);
        });
    }
}
