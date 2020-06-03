package de.wolfig.fx.treeobjects;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;

public final class FileTreeObject extends RecursiveTreeObject<FileTreeObject> {

    public final File file;
    public final StringProperty name;
    public final StringProperty size;

    public FileTreeObject(File file) {
        this.file = file;
        this.name = new SimpleStringProperty(file.getName());
        this.size = new SimpleStringProperty(file.length() / 1204 + " KB");
    }
}
