package de.wolfig.fx.treeobjects;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class LinkTreeObject extends RecursiveTreeObject<LinkTreeObject> {

    public final StringProperty lineNumber;
    public final StringProperty name;

    public LinkTreeObject(int lineNumber, String name) {
        this.lineNumber = new SimpleStringProperty(String.valueOf(lineNumber));
        this.name = new SimpleStringProperty(name);
    }
}
