package de.wolfig.fx;

import de.wolfig.files.Reader;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStore {

    public static HashMap<File, String> files = new HashMap<>();
    public static ObservableList<FileTreeObject> xmlFiles = FXCollections.observableArrayList();
    public static ObservableList<FileTreeObject> txtFiles = FXCollections.observableArrayList();
    public static ObservableList<FileTreeObject> htFiles = FXCollections.observableArrayList();
    public static ObservableList<FileTreeObject> csvFiles = FXCollections.observableArrayList();
    public static ObservableList<LinkTreeObject> linkList = FXCollections.observableArrayList();
    public static ObservableList<CSVTreeObject> overviewList = FXCollections.observableArrayList();


    public static void loadDataStore() {
        try (Stream<Path> walker = Files.walk(Paths.get(new File("./files").getPath()))) {
            List<File> fileList = walker.filter(Files::isRegularFile).map(x -> new File(String.valueOf(x))).collect(Collectors.toList());
            for (File file : fileList) {
                if(file.getPath().startsWith(".\\files\\xml")) xmlFiles.add(new FileTreeObject(file));
                if(file.getPath().startsWith(".\\files\\txt")) txtFiles.add(new FileTreeObject(file));
                if(file.getPath().startsWith(".\\files\\ht")) htFiles.add(new FileTreeObject(file));
                if(file.getPath().startsWith(".\\files\\csv")) csvFiles.add(new FileTreeObject(file));
                files.put(file, file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Reader reader = new Reader();
        int number = 1;
        for(String line : reader.readFileLineByLine(new File("./list.txt"))) {
            linkList.add(new LinkTreeObject(number, line));
            number++;
        }

        for(String line : reader.readFileLineByLineFromLine(new File("overview.csv"), 2)) overviewList.add(new CSVTreeObject(line));
    }
}
