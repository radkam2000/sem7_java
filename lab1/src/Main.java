import CustomClasses.Odczyt_c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> subjectsArray= new ArrayList<>();
        Integer[] grades = {2,3,4,5};
        Odczyt_c.DoTablicy("przedmioty.txt", subjectsArray);
        boolean isExist = subjectsArray.stream().anyMatch(element -> element.toLowerCase().contains("zaaw"));
        System.out.println("Lista przedmiotów zawiera przedmiot z fragmentem \"zaaw\": " +  isExist);
//        Stream<Object> gradesForSubjects = subjectsArray.stream().map(element->{
//            return new AbstractMap.SimpleEntry<String,Integer>(element,grades[(int) Math.floor(Math.random() * grades.length)]);});
//        gradesForSubjects.forEach(n-> System.out.println(n));
        Stream<Object> gradesForSubjects = subjectsArray.stream().map(element->{return grades[(int) Math.floor(Math.random() * grades.length)];});
        HashMap<Integer,Integer> gradesRepetition = new HashMap<>();
        gradesForSubjects.forEach(element->{
            if(gradesRepetition.get(element)==null) gradesRepetition.put((Integer) element,1);
            gradesRepetition.put((Integer) element,gradesRepetition.get(element)+1);
        });
        System.out.println(gradesRepetition);
        ;


    }
}
