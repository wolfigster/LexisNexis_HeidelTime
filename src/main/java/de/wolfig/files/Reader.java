package de.wolfig.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Reader {

    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    public ArrayList<String> readFileLineByLine(File file) {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
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

    public ArrayList<String> readFileLineByLineFromLine(File file, int startLine) {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader;
        int i = 1;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                if(i >= startLine) {
                    arrayList.add(line);
                }
                i++;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
