package org.example;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String scannerInput;
        System.out.println("Podaj imie");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        String imie = scannerInput;
        System.out.println("Podaj nazwisko");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        String nazwisko = scannerInput;
        System.out.println("Podaj indeks");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        Integer indeks = Integer.valueOf(scannerInput);
        System.out.println("Podaj maila");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        String mail = scannerInput;
        System.out.println("Podaj adres");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        String address = scannerInput;
        System.out.println("Podaj rok urodzenia");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        Integer rok = Integer.valueOf(scannerInput);
        System.out.println("Podaj miesiąc urodzenia");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        Integer miesiac = Integer.valueOf(scannerInput);
        System.out.println("Podaj dzień urodzenia");
        scannerInput = scanner.nextLine();
        checkInput(scannerInput);
        Integer dzien = Integer.valueOf(scannerInput);
        Student s = new Student(imie, nazwisko, indeks, mail, address, new Date(rok-1900, miesiac-1, dzien));
        System.out.println(s.toString());
    }

    public static void checkInput(String input) {
        if (input.length() < 1) {
            System.out.println("Nie podałeś danych");
            System.exit(1);
        }
    }
}