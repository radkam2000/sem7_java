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

        Stream<Map.Entry<String, Integer>> gradesForSubjectsWithNames = subjectsArray.stream().map(element -> {
            return new AbstractMap.SimpleEntry<String, Integer>(element, grades[(int) Math.floor(Math.random() * grades.length)]);
        });
//        gradesForSubjectsWithNames.forEach(n-> System.out.println(n));
        Random rand = new Random();
        HashMap<String, Integer> newMap = subjectsArray.stream()
                .collect(Collectors.toMap(k -> k, v -> grades[(int) Math.floor(Math.random() * grades.length)], (prev, next) -> next, HashMap::new));
        Stream sorted = newMap.entrySet().stream().sorted(HashMap.Entry.<String, Integer>comparingByValue());
        gradesForSubjectsWithNames.sorted(HashMap.Entry.<String,Integer>comparingByValue());
        sorted.forEach(System.out::println);

//        System.out.println("\n\n\n");
        Stream<Object> gradesForSubjects = subjectsArray.stream().map(element -> {
            return grades[(int) Math.floor(Math.random() * grades.length)];
        });
        newMap.forEach((k, v) -> {
//            System.out.println(k.toString() + v.toString());
        });
        HashMap<Integer, Integer> gradesRepetition = new HashMap<>();
        gradesForSubjects.forEach(element -> {
            if (gradesRepetition.get(element) == null) gradesRepetition.put((Integer) element, 1);
            gradesRepetition.put((Integer) element, gradesRepetition.get(element) + 1);
        });
//        System.out.println(gradesRepetition);
        Zapis_c.ZTablicy("wynik.txt", subjectsArray);
    }
}
