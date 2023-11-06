package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class DataChecker {

    public static boolean isValidPassword(String password) {
        return Pattern.matches(".*[a-z].*", password) &&
                Pattern.matches(".*[A-Z].*", password) &&
                Pattern.matches(".*\\d.*", password) &&
                Pattern.matches(".*[!@#\\$%^&*()].*", password);
    }

    public static boolean isUsernameTaken(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Login: " + username + ", ")) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Błąd pliku.");
            e.printStackTrace();
        }
        return false;
    }
}
