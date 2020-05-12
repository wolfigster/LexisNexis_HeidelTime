package de.wolfig.files;

import de.wolfig.response.list.DocumentList;
import de.wolfig.response.list.Value;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private final String CSV_SEPARATOR = ";";
    private final String CSV_LINEBREAK = "\n";
    private BufferedWriter bufferedWriter;
    private File file;
    private boolean append;

    public Writer(File file, boolean append) {
        this.file = file;
        checkForFile();
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(file, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Writer(String filePath, boolean append) {
        this(new File(filePath), append);
    }

    public void changeWriterSettings(File file, boolean append) {
        this.file = file;
        checkForFile();
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(file, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWriterSettings(String filePath, boolean append) {
        this.changeWriterSettings(new File(filePath), append);
    }

    public void changeWriterFile(File file) {
        this.file = file;
        checkForFile();
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(file, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeWriterFile(String filePath) {
        this.changeWriterFile(new File(filePath));
    }

    public void changeWriterAppend(boolean append) {
        this.append = append;
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(file, append));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForFile() {
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

    public void writeRObjectToCSV(DocumentList documentList) {
        for(Value value : documentList.getValue()) {

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
