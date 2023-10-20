package org.example;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Students {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Student")
    List<Student> students = new ArrayList<Student>();

    public Students() {
        students = List.of(
                new Student("name", "surname", 000001, "kamrad@pollub.pl"),
                new Student("Marcin", "Draft", 000002, "mardra@pollub.pl"),
                new Student("Maciek", "Floyd", 000003, "macflo@pollub.pl"),
                new Student("name", "Vive", 000004, "natviv@pollub.pl")
        );
    }
}


public class Main {
    public static void main(String[] args) {
        Students students = new Students();
        System.out.println("Zapisane dane do pliku xml");
        System.out.println(students.students);
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

            FileWriter fileWriter = new FileWriter("students.xml");
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xmlMapper.writeValue(fileWriter, students);
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Błąd w stworzeniu pliku xml");
        }

        Students students2 = null;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            students2 = xmlMapper.readValue(new File("students.xml"), Students.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Błąd w odczycie pliku xml");
        }
        System.out.println("Odczytane dane z pliku xml");
        System.out.println(students2.students);

        Students students3 = null;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            students3 = xmlMapper.readValue(new File("students.xml"), Students.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Błąd w odczycie pliku xml");
        }

        // Sprawdź, czy dane są wystarczające do utworzenia obiektu
        if (students3 != null) {
            for (Student student : students3.students) {
                if (student.name == null || student.surname == null || student.index == null || student.email == null) {
                    System.out.println("Nie można utworzyć obiektu Student. Brakuje wymaganych danych.");
                }
            }
        }

        for (Student student : students3.students) {
            if (student.getName().equals("Marcin")) {
                System.out.println("Wybrane dane wybranego studenta: Imie " + student.getName() + "email " + student.getEmail());
            }
        }


    }
}