package de.wolfig.files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Reader {

    public ArrayList<String> readFileLineByLine() {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./list.txt"), StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                arrayList.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
