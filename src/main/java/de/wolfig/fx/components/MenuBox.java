package de.wolfig.fx.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.wolfig.DateRule;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                // alles mit GridPanes machen -> HBox & VBox durch GridPane ersetzen. -> Letztlich ganz viele GridPanes in einem ScrollPane
                Stage stage = new Stage();
                Properties properties = Configuration.getDateRuleProperties();

                javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane();
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

                GridPane mainPane = initConfigPanels(stage, properties);
                //mainPane.setGridLinesVisible(true);

                scrollPane.setContent(mainPane);
                scrollPane.setOpaqueInsets(new Insets(0));

                stage.setTitle("Configuration: daterule.xml");
                stage.setScene(new Scene(scrollPane, 450, 720));
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

    private GridPane initConfigPanels(Stage stage, Properties properties) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setMinWidth(430);
        gridPane.setPrefWidth(430);
        gridPane.setMaxWidth(430);

        FlowPane titlePane = createConfigTitlePane("DateRule Configuration", 2, 2, Pos.CENTER, 430);
        gridPane.add(titlePane, 0, 0);

        gridPane.add(generalConfigPane(stage, properties), 0, 1);
        int l = 2;
        for(int i = 0; i < DateRule.getRuleAmount(); i++) {
            gridPane.add(createConfigPane(i, false, properties), 0, l);
            l++;
        }
        gridPane.add(createConfigPane(DateRule.getRuleAmount(), true, properties), 0, l);

        return gridPane;
    }

    private GridPane generalConfigPane(Stage stage, Properties properties) {
        GridPane gridPane = new GridPane();
        gridPane.setId("generalConfig");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(createConfigTitlePane("General", 1.25, 1.25, Pos.TOP_LEFT, 410), 0, 0, 4, 1);
        int i = 1;
        for(String property : properties.stringPropertyNames()) {
            if(!property.startsWith("duration") && !property.startsWith("date")) {
                Label label = property.contains(".") ? new Label(property.split("\\.")[1].substring(0,1).toUpperCase() + property.split("\\.")[1].substring(1)) : new Label(property.substring(0,1).toUpperCase() + property.substring(1));
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefWidth(100);
                JFXTextField textField = new JFXTextField(properties.getProperty(property));
                textField.setId(label.getText().toLowerCase());
                textField.setPrefWidth(45);
                gridPane.add(label, 0, i);
                gridPane.add(textField, 1, i);
                i++;
            }
        }

        JFXButton saveButton = new JFXButton("SAVE");
        saveButton.setStyle("-fx-pref-width: 100px;" + "-fx-background-color: rgba(200,100,100,0.5);" + "-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
        saveButton.setOnMouseClicked((clickClose) -> {

            Thread thread = new Thread("saveButton Thread") {
                public void run() {
                    Properties props = new Properties();
                    ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
                    for(Node node : gridPane.getParent().getChildrenUnmodifiable()) {
                        if(node.getId() != null) {

                            if(node.getId().equals("generalConfig")) {
                                for(Node innerNode : ((GridPane) node).getChildrenUnmodifiable()) {
                                    if(innerNode.getClass().equals(JFXTextField.class)) {
                                        props.setProperty(innerNode.getId(), ((JFXTextField) innerNode).getText());
                                    }
                                }
                            }

                            if(node.getId().startsWith("config")) {
                                HashMap<String, String> propertyMap = new HashMap<>();

                                for(Node innerNode : ((GridPane) node).getChildrenUnmodifiable()) {
                                    if(innerNode.getClass().equals(JFXTextField.class)) {
                                        propertyMap.put(innerNode.getId(), ((JFXTextField) innerNode).getText());
                                    }
                                }
                                hashMaps.add(propertyMap);
                            }
                        }
                    }
                    HashMap<String, String> hMap = new HashMap<>();
                    int amount = 0;
                    for(HashMap<String, String> map : hashMaps) {
                        boolean empty = false;
                        for(String val : map.values())
                            if (val.equals("") || !val.matches("first|mid|last|ht|txt|mo|tu|we|th|fr|sa|su|[0-3][0-9].[0-1][0-9]|[0-3][0-9]d|[0-9]{1,2}")) {
                                empty = true;
                                break;
                            }
                        if(!empty) {
                            amount++;
                            for(Map.Entry<String, String> entry : map.entrySet()) {
                                if(!hMap.containsKey(entry.getKey())) hMap.put(entry.getKey(), entry.getValue());
                                else hMap.put(entry.getKey(), hMap.get(entry.getKey()) + ";" + entry.getValue());
                            }
                        }
                    }
                    properties.setProperty("ruleamount", String.valueOf(amount));
                    for(Map.Entry<String, String> entry : hMap.entrySet()) {
                        properties.setProperty(entry.getKey(), entry.getValue());
                    }
                    Configuration.saveDateRules(properties);
                }
            };
            thread.start();

            // save all textfields über die verschiedenen gridpanes loopen, danach die die grids wo date hat mit date verbinden und duration mit duration dann abspeichern und fertig
            stage.close();
        });
        JFXButton closeButton = new JFXButton("CLOSE");
        closeButton.setStyle("-fx-pref-width: 100px;" + "-fx-background-color: rgba(200,100,100,0.5);" + "-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");
        closeButton.setOnMouseClicked((clickClose) -> {
            stage.close();
        });

        gridPane.add(saveButton, 2, 1);
        gridPane.add(closeButton, 2, 2);

        gridPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");

        return gridPane;
    }

    private GridPane createConfigPane(int number, boolean empty, Properties properties) {
        GridPane gridPane = new GridPane();
        gridPane.setId("config" + (number+1));
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(createConfigTitlePane("Config " + (number+1), 1.5, 1.5, Pos.TOP_LEFT, 390), 0, 0, 4, 1);

        int i = 2;
        int k = 2;
        gridPane.add(createConfigTitlePane("Date Rules", 1.25, 1.25, Pos.TOP_LEFT, 175), 0, 1, 2, 1);
        gridPane.add(createConfigTitlePane("Duration Rules", 1.25, 1.25, Pos.TOP_LEFT, 175), 2, 1, 2, 1);
        for(String property : properties.stringPropertyNames()) {
            if(property.startsWith("duration") || property.startsWith("date")) {
                Label label = property.contains(".") ? new Label(property.split("\\.")[1].substring(0, 1).toUpperCase() + property.split("\\.")[1].substring(1)) : new Label(property.substring(0, 1).toUpperCase() + property.substring(1));
                label.setAlignment(Pos.CENTER_RIGHT);
                label.setPrefWidth(100);
                JFXTextField textField;
                if(!empty) {
                    textField = new JFXTextField(properties.getProperty(property).split(";")[number]);
                } else {
                    textField = new JFXTextField("");
                }
                textField.setPrefWidth(45);
                if (property.startsWith("duration")) {
                    textField.setId("duration." + label.getText().toLowerCase());
                    gridPane.add(label, 2, i);
                    gridPane.add(textField, 3, i);
                    i++;
                } else if (property.startsWith("date")) {
                    textField.setId("date." + label.getText().toLowerCase());
                    gridPane.add(label, 0, k);
                    gridPane.add(textField, 1, k);
                    k++;
                }
            }
        }
        gridPane.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-color: #C86464;");

        return gridPane;
    }

    private FlowPane createConfigTitlePane(String name, double scaleX, double scaleY, Pos pos, double width) {
        Label title = new Label(name);
        title.setScaleX(scaleX);
        title.setScaleY(scaleY);

        FlowPane flowPane = new FlowPane(title);
        flowPane.setPadding(new Insets(10));
        flowPane.setAlignment(pos);
        flowPane.setMinWidth(width);
        flowPane.setPrefWidth(width);
        flowPane.setMaxWidth(width);
        //flowPane.setStyle("-fx-background-color: rgba(1,100,100,0.5);");

        return flowPane;
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
