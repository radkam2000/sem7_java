package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Register {
    public static boolean run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rejestracja użytkownika");

        System.out.print("Login: ");
        String username = scanner.nextLine();

        if (DataChecker.isUsernameTaken(username)) {
            System.out.println("Login zajęty.");
            return false;
        }

        System.out.print("Hasło: ");
        String password = scanner.nextLine();

        if (password.length() < 8) {
            System.out.println("Hasło jest zbyt krótkie. Minimum 8 znaków.");
            return false;
        }

        if (!DataChecker.isValidPassword(password)) {
            System.out.println("Hasło musi zawierać co najmniej jedną małą literę, jedną dużą literę, cyfrę i znak specjalny.");
            return false;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.txt", true));
            writer.write("Login: " + username + ", Hasło: " + Hash.password(password));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.err.println("Błąd pliku.");
            e.printStackTrace();
        }
        return true;
    }
}
