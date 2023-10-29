package com.example.lab3_javafx;

class Vigenere {

    public static String encrypt(String message, String key) {
        message = message.trim();
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int c = message.charAt(i) + key.charAt(i % key.length()) - 48;
            if (c > 122) {
                while (c > 122) {
                    c -= 75;
                }
            } else if (c < 48) {
                while (c < 48) {
                    c += 75;
                }
            }
            encrypted += (char) c;
        }
        return encrypted;
    }

    public static String decrypt(String message, String key) {
        message = message.trim();
        String decrypted = "";
        for (int i = 0; i < message.length(); i++) {
            int c = message.charAt(i) - key.charAt(i % key.length()) + 48;
            if (c > 122) {
                while (c > 122) {
                    c -= 75;
                }
            } else if (c < 48) {
                while (c < 48) {
                    c += 75;
                }
            }
            decrypted += (char) c;
        }
        return decrypted;
    }
}