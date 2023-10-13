package CustomClasses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

public class Zapis_c {

    public static void ZStreamu(String filePath, Stream<Map.Entry<String,Integer>> arr) {
        File file = new File("WynikZapisu.txt");
        try{
            FileWriter fileWriter = new FileWriter(file,true);
            arr.forEach(elem-> {
                try {
                    fileWriter.append(elem.toString()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
//            for (int i=0;i<arr.size();i++){
//                fileWriter.append(arr.get(i) +"\n");
//            }
            fileWriter.close();
        }catch(IOException ex){
            System.err.println(ex.getCause());
        }
    }
}