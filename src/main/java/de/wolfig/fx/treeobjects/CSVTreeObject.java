package de.wolfig.fx.treeobjects;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.wolfig.fx.DataStore;
import javafx.beans.property.*;

public final class CSVTreeObject extends RecursiveTreeObject<CSVTreeObject> {

    public final StringProperty urn;
    public final StringProperty title;
    public final StringProperty date;
    public final StringProperty wordLength;
    public final StringProperty document;

    public final BooleanProperty xmlExists;
    public final BooleanProperty txtExists;
    public final BooleanProperty htExists;
    public final BooleanProperty csvExists;

    public CSVTreeObject(String row) {
        String[] data = row.split(";");
        this.urn = new SimpleStringProperty(data[0]);
        this.title = new SimpleStringProperty(data[1]);
        this.date = new SimpleStringProperty(data[2]);
        this.wordLength = new SimpleStringProperty(String.valueOf(data[3]));
        this.document = new SimpleStringProperty(data[4]);
        this.xmlExists = new SimpleBooleanProperty(DataStore.files.containsValue(".\\files\\xml\\" + title + ".xml"));
        this.txtExists = new SimpleBooleanProperty(DataStore.files.containsValue(".\\files\\txt\\" + title + ".txt"));
        this.htExists = new SimpleBooleanProperty(DataStore.files.containsValue(".\\files\\ht\\" + title + ".xml"));
        this.csvExists = new SimpleBooleanProperty(DataStore.files.containsValue(".\\files\\csv\\" + title + ".csv"));
    }
}
