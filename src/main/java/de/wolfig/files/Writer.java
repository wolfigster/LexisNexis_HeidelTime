package de.wolfig.files;

import de.wolfig.response.RObject;
import de.wolfig.response.Value;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private final String CSV_SEPARATOR = ";";
    private final String CSV_LINEBREAK = "\n";
    private BufferedWriter bufferedWriter;
    private String filePath;
    private boolean append;

    public Writer(String filePath, boolean append) {
        this.filePath = filePath;
        checkForFile();
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filePath, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWriterSettings(String filePath, boolean append) {
        this.filePath = filePath;
        checkForFile();
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filePath, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWriterFile(String filePath) {
        this.filePath = filePath;
        checkForFile();
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filePath, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWriterAppend(boolean append) {
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filePath, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForFile() {
        File file = new File(filePath);
        if(file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String string) {
        if(!append) {
            try {
                bufferedWriter.write(string);
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                bufferedWriter.append(string);
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeRObjectToCSV(RObject rObject) {
        for(Value value : rObject.getValue()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(value.getResultId()).append(CSV_SEPARATOR);
            stringBuilder.append(value.getTitle()).append(CSV_SEPARATOR);
            stringBuilder.append(value.getDate()).append(CSV_SEPARATOR);
            stringBuilder.append(value.getWordLength()).append(CSV_SEPARATOR);
            stringBuilder.append(value.getDocumentContentOdataMediaReadLink()).append(CSV_LINEBREAK);

            writeToFile(stringBuilder.toString());
        }
    }

    public void closeWriter() {
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
