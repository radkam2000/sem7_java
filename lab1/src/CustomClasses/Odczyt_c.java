package CustomClasses;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Odczyt_c {
    public static void DoTablicy(String filePath, ArrayList arr) {
        try {
            BufferedReader bufferedReader = new
                    BufferedReader(new FileReader(new File(filePath)));
            String linia = null;
            while ((linia = bufferedReader.readLine()) != null) {
                arr.add(linia);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Pliku nie odnaleziono!");
            System.err.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println("Błąd odczytu pliku spowodowany:");
            System.err.println(ex.getCause());
        }
    }

    public static void DoMapy(String filePath, HashMap<String, Float> map) {
        try {
            BufferedReader bufferedReader = new
                    BufferedReader(new FileReader(new File(filePath)));
            String linia = null;
            while ((linia = bufferedReader.readLine()) != null) {
                String keyValuePair[] = linia.split("=", 2);
                map.put(keyValuePair[0], Float.valueOf(keyValuePair[1]));
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Pliku nie odnaleziono!");
            System.err.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println("Błąd odczytu pliku spowodowany:");
            System.err.println(ex.getCause());
        }
    }
}