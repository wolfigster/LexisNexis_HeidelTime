package de.wolfig.fx.components;

import com.jfoenix.controls.JFXButton;
import de.wolfig.Worker;
import de.wolfig.fx.DataStore;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuBox extends HBox {

    static JFXButton reloadButton = new JFXButton("Reload Files");
    static JFXButton requestDocumentButton = new JFXButton("Request Document");
    static JFXButton requestListButton = new JFXButton("Request Link");
    static JFXButton convertButton = new JFXButton("Convert XML");
    static JFXButton heideltimeButton = new JFXButton("Run Heideltime");
    static JFXButton csvButton = new JFXButton("Create CSV");
    static JFXButton emptyButton = new JFXButton("");
    static JFXButton githubButton = new JFXButton("Open GitHub");

    public MenuBox() {

        requestDocumentButton.setDisable(false);
        requestListButton.setDisable(true);
        convertButton.setDisable(true);
        heideltimeButton.setDisable(true);
        csvButton.setDisable(true);
        emptyButton.setDisable(true);
        githubButton.setDisable(false);

        reloadButton.getStyleClass().add("button-menu");
        requestDocumentButton.getStyleClass().add("button-menu");
        requestListButton.getStyleClass().add("button-menu");
        convertButton.getStyleClass().add("button-menu");
        heideltimeButton.getStyleClass().add("button-menu");
        csvButton.getStyleClass().add("button-menu");
        emptyButton.getStyleClass().add("button-menu");
        githubButton.getStyleClass().add("button-menu");

        reloadButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Reload Files Thread") {
                public void run(){
                    DataStore.reloadDataStore();
                }
            };
            thread.start();
        });

        requestDocumentButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Request Document Thread") {
                public void run(){
                    int count = ListBox.csvTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems().size();
                    int curr = 1;
                    ListBox.setProgressBar("requestDocument", true);
                    ListBox.updateProgressBar("requestDocument", 0, count);
                    for(TreeItem<CSVTreeObject> csvTreeObjectTreeItem : ListBox.csvTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        Worker.requestDocument(csvTreeObjectTreeItem.getValue().title.get(), csvTreeObjectTreeItem.getValue().document.get());
                        DataStore.xmlFiles.add(new FileTreeObject(new File("." + File.separator + "files" + File.separator + "xml" + File.separator + csvTreeObjectTreeItem.getValue().title.get() + ".xml")));
                        ListBox.updateProgressBar("requestDocument", curr, count);
                        curr++;
                    }
                    ListBox.setProgressBar("requestDocument", false);
                }
            };
            thread.start();
        });

        requestListButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Request List Thread") {
                public void run() {
                    int count = ListBox.linkTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems().size();
                    int curr = 1;
                    ListBox.setProgressBar("requestList", true);
                    ListBox.updateProgressBar("requestList", 0, count);
                    for (TreeItem<LinkTreeObject> linkTreeObjectTreeItem : ListBox.linkTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        String response = Worker.requestListItem(linkTreeObjectTreeItem.getValue().name.get());
                        for(String res : response.split("\n")) DataStore.overviewList.add(new CSVTreeObject(res));
                        ListBox.updateProgressBar("requestList", curr, count);
                        curr++;
                    }
                    ListBox.setProgressBar("requestList", false);
                }
            };
            thread.start();
        });

        convertButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Convert XML to XML Thread") {
                public void run() {
                    int count = ListBox.xmlFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems().size();
                    int curr = 1;
                    ListBox.setProgressBar("convert", true);
                    ListBox.updateProgressBar("convert", 0, count);
                    for(TreeItem<FileTreeObject> fileTreeObject : ListBox.xmlFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        Worker.convertXMLtoTXT(fileTreeObject.getValue().file.getPath());
                        DataStore.txtFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("xml", "txt"))));
                        ListBox.updateProgressBar("convert", curr, count);
                        curr++;
                    }
                    ListBox.setProgressBar("convert", false);
                }
            };
            thread.start();
        });

        heideltimeButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Execute Heideltime Thread") {
                public void run() {
                    int count = ListBox.txtFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems().size();
                    int curr = 1;
                    ListBox.setProgressBar("heideltime", true);
                    ListBox.updateProgressBar("heideltime", 0, count);
                    for(TreeItem<FileTreeObject> fileTreeObject : ListBox.txtFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        Worker.executeHeidelTime(fileTreeObject.getValue().file.getPath());
                        DataStore.htFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("txt", "xml").replaceFirst("xml", "ht"))));
                        ListBox.updateProgressBar("heideltime", curr, count);
                        curr++;
                    }
                    ListBox.setProgressBar("heideltime", false);
                }
            };
            thread.start();
        });

        csvButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("Convert XML to CSV Thread") {
                public void run() {
                    int count = ListBox.htFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems().size();
                    int curr = 1;
                    ListBox.setProgressBar("csv", true);
                    ListBox.updateProgressBar("csv", 0, count);
                    for(TreeItem<FileTreeObject> fileTreeObject : ListBox.htFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        Worker.createCSV(fileTreeObject.getValue().file.getPath());
                        DataStore.csvFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("ht", "csv").replaceFirst("xml", "csv"))));
                        ListBox.updateProgressBar("csv", curr, count);
                        curr++;
                    }
                    ListBox.setProgressBar("csv", false);
                }
            };
            thread.start();
        });

        emptyButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("emptyButton Thread") {
                public void run() {
                    // Do nothing
                }
            };
            thread.start();
        });

        githubButton.setOnMouseClicked((click) -> {
            Thread thread = new Thread("GitHub Thread") {
                public void run() {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://github.com/wolfigster/LexisNexis_HeidelTime"));
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        });

        this.setPrefSize(1200, 50);
        //this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().addAll(reloadButton, requestDocumentButton, requestListButton, convertButton, heideltimeButton, csvButton, emptyButton, githubButton);
    }

    static void setCurrentTab(String text) {
        requestDocumentButton.setDisable(true);
        requestListButton.setDisable(true);
        convertButton.setDisable(true);
        heideltimeButton.setDisable(true);
        csvButton.setDisable(true);
        switch (text) {
            case "Overview":
                if(!ListBox.requestDocumentProgressBar.isVisible()) requestDocumentButton.setDisable(false);
                break;
            case "List":
                if(!ListBox.requestListProgressBar.isVisible()) requestListButton.setDisable(false);
                break;
            case "files/xml":
                if(!ListBox.convertProgressBar.isVisible()) convertButton.setDisable(false);
                break;
            case "files/txt":
                if(!ListBox.heideltimeProgressBar.isVisible()) heideltimeButton.setDisable(false);
                break;
            case "files/ht":
                if(!ListBox.csvProgressBar.isVisible()) csvButton.setDisable(false);
                break;
        }
    }
}
