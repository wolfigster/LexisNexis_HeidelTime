package de.wolfig.fx;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {

    private JFXTreeTableView csvTreeObjectJFXTreeTableView;
    private JFXTreeTableView linkTreeObjectJFXTreeTableView;
    private JFXTreeTableView xmlFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView txtFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView htFileTreeObjectJFXTreeTableView;
    private JFXTreeTableView csvFileTreeObjectJFXTreeTableView;

    @Override
    public void start(Stage stage) {
        DataStore.loadDataStore();
        loadTreeTableViews();


        // menu
        HBox menuBox = new HBox();
        menuBox.setPrefSize(1200, 50);
        menuBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        menuBox.getChildren().addAll();

        // main
        JFXTabPane tabListPane = new JFXTabPane();
        Tab overviewTab = new Tab();
        overviewTab.setText("Overview");
        overviewTab.setContent(csvTreeObjectJFXTreeTableView);
        Tab listTab = new Tab();
        listTab.setText("List");
        listTab.setContent(linkTreeObjectJFXTreeTableView);
        Tab xmlTab = new Tab();
        xmlTab.setText("files/xml");
        xmlTab.setContent(xmlFileTreeObjectJFXTreeTableView);
        Tab txtTab = new Tab();
        txtTab.setText("files/txt");
        txtTab.setContent(txtFileTreeObjectJFXTreeTableView);
        Tab htTab = new Tab();
        htTab.setText("files/ht");
        htTab.setContent(htFileTreeObjectJFXTreeTableView);
        Tab csvTab = new Tab();
        csvTab.setText("files/csv");
        csvTab.setContent(csvFileTreeObjectJFXTreeTableView);
        tabListPane.getTabs().addAll(overviewTab, listTab, xmlTab, txtTab, htTab, csvTab);

        // main-list
        VBox listBox = new VBox();
        listBox.setPrefSize(600, 550);
        listBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        listBox.getChildren().addAll(tabListPane);

        // main-detail
        VBox infoBox = new VBox();
        infoBox.setPrefSize(600, 50);
        infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        infoBox.getChildren().addAll();

        VBox fileBox = new VBox();
        fileBox.setPrefSize(600, 500);
        fileBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        fileBox.getChildren().addAll();

        VBox detailBox = new VBox();
        detailBox.setPrefSize(600, 550);
        detailBox.getChildren().addAll(infoBox, fileBox);

        // main-main
        HBox mainBox = new HBox();
        mainBox.setPrefSize(1200, 550);
        mainBox.getChildren().addAll(listBox, detailBox);

        // scene
        final Scene scene = new Scene(new StackPane(new FlowPane(menuBox, mainBox)), 1200, 600);
        scene.getStylesheets().addAll(Window.class.getResource("/css/jfoenix-components.css").toExternalForm(),
                Window.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                Window.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
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

    private JFXTreeTableView loadFileTreeObjectTableView(ObservableList<FileTreeObject> fileTreeObjects) {
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

    public static void main(String... args) {
        launch(args);
    }
}
