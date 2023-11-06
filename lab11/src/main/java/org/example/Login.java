package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Login {

    public static boolean run(){

            int maxAttempts = 3;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Logowanie");
            while (maxAttempts > 0) {
                System.out.print("Login: ");
                String username = scanner.nextLine();

                System.out.print("Hasło: ");
                String password = scanner.nextLine();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("src/users.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("Login: " + username + ", ") && line.contains("Hasło: " + Hash.password(password))) {
                            reader.close();
                            return true;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Błąd pliku.");
                    e.printStackTrace();
                }
                maxAttempts--;
                if (maxAttempts > 0) {
                    System.out.println("Logowanie nieudane. Pozostało prób: " + maxAttempts);
                } else {
                    System.out.println("Osiągnięto limit prób.");
                }
            }
            return false;
        }
    }
