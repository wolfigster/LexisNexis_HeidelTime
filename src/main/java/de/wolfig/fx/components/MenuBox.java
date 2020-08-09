package de.wolfig.fx.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.wolfig.Worker;
import de.wolfig.files.Configuration;
import de.wolfig.fx.DataStore;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class MenuBox extends HBox {

    static JFXButton reloadButton = new JFXButton("Reload Files");
    static JFXButton requestDocumentButton = new JFXButton("Request Document");
    static JFXButton requestListButton = new JFXButton("Request Link");
    static JFXButton convertButton = new JFXButton("Convert XML");
    static JFXButton heideltimeButton = new JFXButton("Run Heideltime");
    static JFXButton csvButton = new JFXButton("Create CSV");
    static JFXButton configurationButton = new JFXButton("Edit Config");
    static JFXButton githubButton = new JFXButton("Open GitHub");

    public MenuBox() {

        requestDocumentButton.setDisable(false);
        requestListButton.setDisable(true);
        convertButton.setDisable(true);
        heideltimeButton.setDisable(true);
        csvButton.setDisable(true);
        configurationButton.setDisable(false);
        githubButton.setDisable(false);

        reloadButton.getStyleClass().add("button-menu");
        requestDocumentButton.getStyleClass().add("button-menu");
        requestListButton.getStyleClass().add("button-menu");
        convertButton.getStyleClass().add("button-menu");
        heideltimeButton.getStyleClass().add("button-menu");
        csvButton.getStyleClass().add("button-menu");
        configurationButton.getStyleClass().add("button-menu");
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

        configurationButton.setOnMouseClicked((click) -> {
            try {
                Stage stage = new Stage();
                Properties properties = Configuration.getDateRuleProperties();

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new Insets(20));
                gridPane.setHgap(5);
                gridPane.setVgap(5);
                Label title = new Label("DateRule Configuration");
                title.setScaleY(2);
                title.setScaleX(2);
                title.setAlignment(Pos.CENTER);
                FlowPane flowPane = new FlowPane(title);
                flowPane.setPadding(new Insets(10));
                flowPane.setAlignment(Pos.CENTER);
                gridPane.add(flowPane, 0, 0, 2, 1);

                VBox normalProperties = new VBox(new Label("General"));
                normalProperties.setSpacing(20);
                normalProperties.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
                VBox dateProperties = new VBox(new Label("Date"));
                dateProperties.setSpacing(20);
                dateProperties.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
                VBox durationProperties = new VBox(new Label("Duration"));
                durationProperties.setSpacing(20);
                durationProperties.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");

                for(String property : properties.stringPropertyNames()) {
                    HBox hBox = new HBox();
                    hBox.setSpacing(10);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    Label label = property.contains(".") ? new Label(property.split("\\.")[1].substring(0,1).toUpperCase() + property.split("\\.")[1].substring(1)) : new Label(property.substring(0,1).toUpperCase() + property.substring(1));
                    label.setAlignment(Pos.CENTER_RIGHT);
                    label.setPrefWidth(100);
                    JFXTextField textField = new JFXTextField(properties.getProperty(property));
                    textField.setPrefWidth(45);
                    hBox.getChildren().addAll(label, textField);
                    if(property.startsWith("date")) {
                        dateProperties.getChildren().add(hBox);
                    } else if(property.startsWith("duration")) {
                        durationProperties.getChildren().add(hBox);
                    } else {
                        normalProperties.getChildren().add(hBox);
                    }
                }

                gridPane.add(normalProperties, 0, 1);
                gridPane.add(durationProperties, 0, 2);
                gridPane.add(dateProperties, 1, 1, 1, 2);

                JFXButton saveButton = new JFXButton("SAVE");
                saveButton.setStyle("-fx-background-color: rgba(200,100,100,0.5);" + "-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
                saveButton.setOnMouseClicked((clickSave) -> {
                    Thread thread = new Thread("saveButton Thread") {
                        public void run() {
                            String propName = null;
                            String propValue = null;
                            for(Node node : normalProperties.getChildren()) {
                                if(node.getClass().equals(HBox.class)) {
                                    for(Node n : ((HBox) node).getChildren()) {
                                        System.out.println(n);
                                        if(n.getClass().equals(JFXTextField.class)) {
                                            JFXTextField jfxTextField = (JFXTextField) n;
                                            propValue = jfxTextField.getText();
                                        } else if(n.getClass().equals(Label.class)) {
                                            Label label = (Label) n;
                                            propName = label.getText();
                                        }
                                    }
                                    properties.setProperty(propName.toLowerCase(), propValue);
                                }
                            }
                            for(Node node : durationProperties.getChildren()) {
                                if(node.getClass().equals(HBox.class)) {
                                    for(Node n : ((HBox) node).getChildren()) {
                                        if(n.getClass().equals(JFXTextField.class)) {
                                            JFXTextField jfxTextField = (JFXTextField) n;
                                            propValue = jfxTextField.getText();
                                        } else if(n.getClass().equals(Label.class)) {
                                            Label label = (Label) n;
                                            propName = label.getText();
                                        }
                                    }
                                    properties.setProperty("duration." + propName.toLowerCase(), propValue);
                                }
                            }
                            for(Node node : dateProperties.getChildren()) {
                                if(node.getClass().equals(HBox.class)) {
                                    for(Node n : ((HBox) node).getChildren()) {
                                        if(n.getClass().equals(JFXTextField.class)) {
                                            JFXTextField jfxTextField = (JFXTextField) n;
                                            propValue = jfxTextField.getText();
                                        } else if(n.getClass().equals(Label.class)) {
                                            Label label = (Label) n;
                                            propName = label.getText();
                                        }
                                    }
                                    properties.setProperty("date." + propName.toLowerCase(), propValue);
                                }
                            }
                            // save properties from textfields.
                            Configuration.saveDateRules(properties);
                        }
                    };
                    thread.start();
                });
                JFXButton closeButton = new JFXButton("CLOSE");
                closeButton.setStyle("-fx-background-color: rgba(200,100,100,0.5);" + "-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
                closeButton.setOnMouseClicked((clickClose) -> {
                    stage.close();
                });

                HBox buttonBox = new HBox();
                buttonBox.setSpacing(10);
                buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
                buttonBox.getChildren().addAll(saveButton, closeButton);
                gridPane.add(buttonBox, 1, 3);
                stage.setTitle("Configuration: daterule.xml");
                stage.setScene(new Scene(gridPane, 390, 660));
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        this.getChildren().addAll(reloadButton, requestDocumentButton, requestListButton, convertButton, heideltimeButton, csvButton, configurationButton, githubButton);
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
