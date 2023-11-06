package org.example;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz opcje:");
        System.out.println("1.Rejestracja");
        System.out.println("2.Logowanie");

        String option = scanner.nextLine();
        switch (option){
            case "1":
                if (Register.run()) {
                    System.out.println("Rejestracja udana");
                } else {
                    System.out.println("Rejestracja nieudana.");
                }
                break;

            case "2":
                if (Login.run()) {
                    System.out.println("Logowanie udane.");
                } else {
                    System.out.println("Logowanie nieudane. Spróbuj ponownie lub skontaktuj się z obsługą.");
                }
                break;
            default:
                System.exit(1);
        }
    }
}
