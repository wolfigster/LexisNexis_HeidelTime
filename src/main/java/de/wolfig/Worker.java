package de.wolfig;

import de.wolfig.response.RObject;
import de.wolfig.response.Value;
import de.wolfig.files.Configuration;
import de.wolfig.files.Reader;
import de.wolfig.lexisnexis.Requester;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Worker {

    private Requester requester = null;
    private Reader reader = null;

    public Worker() {
        requester = new Requester(Configuration.getAccessToken());
        reader = new Reader();
    }

    public void initializeList() {

        for(String listItem : reader.readFileLineByLine()) {
            RObject RObject = requester.requestList(listItem);
            writeToCSV(RObject);
        }
    }


    private static final String CSV_SEPARATOR = ";";
    private static void writeToCSV(RObject RObject) {
        String content = "";
        try {
            File callsFile = new File("./response.csv");
            if(callsFile.exists()) {
                content = new Scanner(callsFile).useDelimiter("\\Z").next();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./response.csv"), StandardCharsets.UTF_8));
            if(!content.equals("")) {
                bw.write(content);
                bw.newLine();
            }
            for (Value value : RObject.getValue()) {
                StringBuffer oneLine = new StringBuffer();

                oneLine.append(value.getResultId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(value.getTitle());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(value.getDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(value.getWordLength());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(value.getDocumentContentOdataMediaReadLink());

                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
