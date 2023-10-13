package CustomClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Zapis_c {

    public static void ZTablicy(String filePath, ArrayList arr) {
        File file = new File("WynikZapisu.txt");
        try{
            FileWriter fileWriter = new FileWriter(file,true);
            for (int i=0;i<arr.size();i++){
                fileWriter.append(arr.get(i) +"\n");
            }
            fileWriter.close();
        }catch(IOException ex){
            System.err.println(ex.getCause());
        }
    }
}