package de.wolfig.fx;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.wolfig.Main;
import de.wolfig.Worker;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Window extends Application {

    private JFXTreeTableView<CSVTreeObject> csvTreeObjectJFXTreeTableView;
    private JFXTreeTableView<LinkTreeObject> linkTreeObjectJFXTreeTableView;
    private JFXTreeTableView<FileTreeObject> xmlFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView<FileTreeObject> txtFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView<FileTreeObject> htFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView<FileTreeObject> csvFileTreeObjectJFXTreeTableView;

    private Worker worker;

    @Override
    public void start(Stage stage) {
        DataStore.loadDataStore();
        loadTreeTableViews();
        worker = new Worker();


        // menu
        HBox menuBox = new HBox();
        menuBox.setPrefSize(1200, 50);
        menuBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        menuBox.getChildren().addAll(); // add menu buttons

        // main
        JFXTabPane tabListPane = new JFXTabPane();
        Tab overviewTab = new Tab();
        overviewTab.setText("Overview");
        VBox overviewTabVBox = new VBox();
        HBox overviewTabHBox = new HBox();
        JFXTextField overviewTextField = new JFXTextField();
        overviewTextField.setPrefWidth(550);
        overviewTextField.setMinWidth(550);
        overviewTextField.textProperty().addListener((o, oldVal, newVal) -> {
            csvTreeObjectJFXTreeTableView.setPredicate(props -> {
                final CSVTreeObject csvTreeObject = props.getValue();
                return csvTreeObject.urn.get().contains(newVal)
                        || csvTreeObject.title.get().contains(newVal)
                        || csvTreeObject.date.get().contains(newVal);
            });
        });
        Label overviewSizeLabel = new Label();
        overviewSizeLabel.setPrefWidth(50);
        overviewSizeLabel.setMinWidth(50);
        overviewSizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(csvTreeObjectJFXTreeTableView.getCurrentItemsCount()), csvTreeObjectJFXTreeTableView.currentItemsCountProperty()));
        overviewTabHBox.getChildren().addAll(overviewTextField, overviewSizeLabel);
        overviewTabVBox.getChildren().addAll(csvTreeObjectJFXTreeTableView, overviewTabHBox);
        overviewTab.setContent(overviewTabVBox);


        Tab listTab = new Tab();
        listTab.setText("List");
        VBox listTabVBox = new VBox();
        HBox listTabHBox = new HBox();
        JFXTextField listTextField = new JFXTextField();
        listTextField.setPrefWidth(550);
        listTextField.setMinWidth(550);
        listTextField.textProperty().addListener((o, oldVal, newVal) -> {
            linkTreeObjectJFXTreeTableView.setPredicate(props -> {
                final LinkTreeObject linkTreeObject = props.getValue();
                return linkTreeObject.name.get().contains(newVal);
            });
        });
        Label listSizeLabel = new Label();
        listSizeLabel.setPrefWidth(50);
        listSizeLabel.setMinWidth(50);
        listSizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(linkTreeObjectJFXTreeTableView.getCurrentItemsCount()), linkTreeObjectJFXTreeTableView.currentItemsCountProperty()));
        listTabHBox.getChildren().addAll(listTextField, listSizeLabel);
        listTabVBox.getChildren().addAll(linkTreeObjectJFXTreeTableView, listTabHBox);
        listTab.setContent(listTabVBox);

        tabListPane.getTabs().addAll(overviewTab, listTab, createFilesTab("xml", xmlFileTreeObjectJFXTreeTableView), createFilesTab("txt", txtFileTreeObjectJFXTreeTableView), createFilesTab("ht", htFileTreeObjectJFXTreeTableView), createFilesTab("csv", csvFileTreeObjectJFXTreeTableView));

        // main-list
        VBox listBox = new VBox();
        listBox.setPrefSize(600, 600);
        listBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        listBox.getChildren().addAll(tabListPane);

        // main-detail
        VBox infoBox = new VBox();
        infoBox.setPrefSize(600, 50);
        infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        infoBox.getChildren().addAll();

        VBox fileBox = new VBox();
        fileBox.setPrefSize(600, 550);
        fileBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        fileBox.getChildren().addAll();

        VBox detailBox = new VBox();
        detailBox.setPrefSize(600, 600);
        detailBox.getChildren().addAll(infoBox, fileBox);

        // main-main
        HBox mainBox = new HBox();
        mainBox.setPrefSize(1200, 600);
        mainBox.getChildren().addAll(listBox, detailBox);

        // scene
        final Scene scene = new Scene(new StackPane(new FlowPane(mainBox)), 1200, 600);
        scene.getStylesheets().addAll(Main.class.getResource("/css/jfoenix-components.css").toExternalForm(),
                Main.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        stage.setTitle("LexisNexis Heideltime");
        stage.setMinWidth(1200);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private void loadTreeTableViews() {
        xmlFileTreeObjectJFXTreeTableView = loadFileTreeObjectTableView(DataStore.xmlFiles);
        txtFileTreeObjectJFXTreeTableView = loadFileTreeObjectTableView(DataStore.txtFiles);
        htFileTreeObjectJFXTreeTableView = loadFileTreeObjectTableView(DataStore.htFiles);
        csvFileTreeObjectJFXTreeTableView = loadFileTreeObjectTableView(DataStore.csvFiles);

        JFXTreeTableColumn<LinkTreeObject, String> lineNumberColumn = new JFXTreeTableColumn<>("Number");
        lineNumberColumn.setPrefWidth(50);
        lineNumberColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<LinkTreeObject, String> param) -> {
            if (lineNumberColumn.validateValue(param)) {
                return param.getValue().getValue().lineNumber;
            } else {
                return lineNumberColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<LinkTreeObject, String> nameColumn = new JFXTreeTableColumn<>("Name");
        nameColumn.setPrefWidth(500);
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<LinkTreeObject, String> param) -> {
            if (nameColumn.validateValue(param)) {
                return param.getValue().getValue().name;
            } else {
                return nameColumn.getComputedValue(param);
            }
        });

        linkTreeObjectJFXTreeTableView = new JFXTreeTableView<>(new RecursiveTreeItem<>(DataStore.linkList, RecursiveTreeObject::getChildren));
        linkTreeObjectJFXTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        linkTreeObjectJFXTreeTableView.setShowRoot(false);
        linkTreeObjectJFXTreeTableView.getColumns().setAll(lineNumberColumn, nameColumn);

        csvTreeObjectJFXTreeTableView = new JFXTreeTableView<>(new RecursiveTreeItem<>(DataStore.overviewList, RecursiveTreeObject::getChildren));
        csvTreeObjectJFXTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        csvTreeObjectJFXTreeTableView.setShowRoot(false);
        String[] csvTreeObjectStringVars = {"URN", "Title", "Date", "WordLength", "Document"};
        for(String csvVar : csvTreeObjectStringVars) {
            JFXTreeTableColumn<CSVTreeObject, String> column = new JFXTreeTableColumn<>(csvVar);
            column.setCellValueFactory((TreeTableColumn.CellDataFeatures<CSVTreeObject, String> param) -> {
                if (column.validateValue(param)) {
                    switch (csvVar) {
                        case "URN":
                            return param.getValue().getValue().urn;
                        case "Title":
                            return param.getValue().getValue().title;
                        case "Date":
                            return param.getValue().getValue().date;
                        case "WordLength":
                            return param.getValue().getValue().wordLength;
                        case "Document":
                            return param.getValue().getValue().document;
                        default:
                            return column.getComputedValue(param);
                    }
                } else {
                    return column.getComputedValue(param);
                }
            });
            csvTreeObjectJFXTreeTableView.getColumns().add(column);
        }
        String[] csvTreeObjectBooleanVars = {"xml", "txt", "ht", "csv"};
        for(String csvVar : csvTreeObjectBooleanVars) {
            JFXTreeTableColumn<CSVTreeObject, Boolean> column = new JFXTreeTableColumn<>(csvVar);
            column.setCellValueFactory((TreeTableColumn.CellDataFeatures<CSVTreeObject, Boolean> param) -> {
                if (column.validateValue(param)) {
                    switch (csvVar) {
                        case "xml":
                            return param.getValue().getValue().xmlExists;
                        case "txt":
                            return param.getValue().getValue().txtExists;
                        case "ht":
                            return param.getValue().getValue().htExists;
                        case "csv":
                            return param.getValue().getValue().csvExists;
                        default:
                            return column.getComputedValue(param);
                    }
                } else {
                    return column.getComputedValue(param);
                }
            });
            csvTreeObjectJFXTreeTableView.getColumns().add(column);
        }
    }

    private JFXTreeTableView<FileTreeObject> loadFileTreeObjectTableView(ObservableList<FileTreeObject> fileTreeObjects) {
        JFXTreeTableColumn<FileTreeObject, String> nameColumn = new JFXTreeTableColumn<>("Name");
        nameColumn.setPrefWidth(500);
        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileTreeObject, String> param) -> {
            if (nameColumn.validateValue(param)) {
                return param.getValue().getValue().name;
            } else {
                return nameColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<FileTreeObject, String> sizeColumn = new JFXTreeTableColumn<>("Size");
        sizeColumn.setPrefWidth(85);
        sizeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<FileTreeObject, String> param) -> {
            if (sizeColumn.validateValue(param)) {
                return param.getValue().getValue().size;
            } else {
                return sizeColumn.getComputedValue(param);
            }
        });

        JFXTreeTableView<FileTreeObject> fileTreeObjectJFXTreeTableView = new JFXTreeTableView<>(new RecursiveTreeItem<>(fileTreeObjects, RecursiveTreeObject::getChildren));
        fileTreeObjectJFXTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fileTreeObjectJFXTreeTableView.setShowRoot(false);
        fileTreeObjectJFXTreeTableView.getColumns().setAll(nameColumn, sizeColumn);
        return fileTreeObjectJFXTreeTableView;
    }

    private Tab createFilesTab(String title, JFXTreeTableView<FileTreeObject> jfxTreeTableView) {
        Tab tab = new Tab();
        tab.setText("files/" + title);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        HBox hBox = new HBox();

        JFXTextField jfxTextField = new JFXTextField();
        jfxTextField.setPrefWidth(550);
        jfxTextField.setMinWidth(550);
        jfxTextField.textProperty().addListener((o, oldVal, newVal) -> {
            jfxTreeTableView.setPredicate(props -> {
                final FileTreeObject fileTreeObject = props.getValue();
                return fileTreeObject.file.getName().contains(newVal)
                        || fileTreeObject.name.get().contains(newVal);
            });
        });

        Label sizeLabel = new Label();
        sizeLabel.setPrefWidth(50);
        sizeLabel.setMinWidth(50);
        sizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(jfxTreeTableView.getCurrentItemsCount()), jfxTreeTableView.currentItemsCountProperty()));

        JFXButton jfxButton = new JFXButton();
        switch (title) {
            case "xml":
                jfxButton.setText("Convert XML to TXT");
                jfxButton.setOnMouseClicked((click) -> {
                    for(TreeItem<FileTreeObject> fileTreeObject : xmlFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        worker.convertXMLtoTXT(fileTreeObject.getValue().file.getPath());
                        DataStore.txtFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("xml", "txt"))));
                    }
                });
                break;
            case "txt":
                jfxButton.setText("Run Heideltime");
                jfxButton.setOnMouseClicked((click) -> {
                    for(TreeItem<FileTreeObject> fileTreeObject : txtFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        worker.executeHeidelTime(fileTreeObject.getValue().file.getPath());
                        DataStore.htFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("txt", "xml").replaceFirst("xml", "ht"))));
                    }
                });
                break;
            case "ht":
                jfxButton.setText("Create CSV file");
                jfxButton.setOnMouseClicked((click) -> {
                    for(TreeItem<FileTreeObject> fileTreeObject : htFileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItems()) {
                        worker.createCSV(fileTreeObject.getValue().file.getPath());
                        DataStore.csvFiles.add(new FileTreeObject(new File(fileTreeObject.getValue().file.getPath().replaceAll("ht", "csv").replaceFirst("xml", "csv"))));
                    }
                });
                break;
            case "csv":
                jfxButton.setDisable(true);
                jfxButton.setVisible(false);
                break;
            default:

                break;
        }
        jfxButton.getStyleClass().add("button-raised");

        hBox.getChildren().addAll(jfxTextField, sizeLabel);
        vBox.getChildren().addAll(jfxTreeTableView, hBox, jfxButton);
        tab.setContent(vBox);
        return tab;
    }

    public static void main(String... args) {
        launch(args);
    }
}
