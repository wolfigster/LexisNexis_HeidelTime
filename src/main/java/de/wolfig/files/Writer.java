package de.wolfig.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private static final Logger LOGGER = LogManager.getLogger(Writer.class);

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

    public void closeWriter() {
        try {
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
