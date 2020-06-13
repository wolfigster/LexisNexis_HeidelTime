package de.wolfig.fx.components;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.wolfig.fx.DataStore;
import de.wolfig.fx.treeobjects.CSVTreeObject;
import de.wolfig.fx.treeobjects.FileTreeObject;
import de.wolfig.fx.treeobjects.LinkTreeObject;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListBox extends VBox {

    static JFXTreeTableView<CSVTreeObject> csvTreeObjectJFXTreeTableView;
    static JFXTreeTableView<LinkTreeObject> linkTreeObjectJFXTreeTableView;
    static JFXTreeTableView<FileTreeObject> xmlFileTreeObjectJFXTreeTableView;
    static JFXTreeTableView<FileTreeObject> txtFileTreeObjectJFXTreeTableView;
    static JFXTreeTableView<FileTreeObject> htFileTreeObjectJFXTreeTableView;
    static JFXTreeTableView<FileTreeObject> csvFileTreeObjectJFXTreeTableView;

    static JFXProgressBar requestDocumentProgressBar = new JFXProgressBar();
    static JFXProgressBar requestListProgressBar = new JFXProgressBar();
    static JFXProgressBar convertProgressBar = new JFXProgressBar();
    static JFXProgressBar heideltimeProgressBar = new JFXProgressBar();
    static JFXProgressBar csvProgressBar = new JFXProgressBar();

    static Label requestDocumentProgress = new Label("0/0");
    static Label requestListProgress = new Label("0/0");
    static Label convertProgress = new Label("0/0");
    static Label heideltimeProgress = new Label("0/0");
    static Label csvProgress = new Label("0/0");

    public ListBox() {
        loadTreeTableViews();

        JFXTabPane tabPane = new JFXTabPane();

        Tab overviewTab = new Tab();
        overviewTab.setText("Overview");
        VBox overviewTabVBox = new VBox();
        overviewTabVBox.setSpacing(10);
        HBox overviewUpperTabHBox = new HBox();
        HBox overviewLowerTabHBox = new HBox();
        JFXTextField overviewTextField = new JFXTextField();
        overviewTextField.setPrefWidth(500);
        overviewTextField.setMinWidth(500);
        overviewTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            csvTreeObjectJFXTreeTableView.setPredicate(props -> {
                final CSVTreeObject csvTreeObject = props.getValue();
                return csvTreeObject.urn.get().contains(newValue)
                        || csvTreeObject.title.get().contains(newValue)
                        || csvTreeObject.date.get().contains(newValue);
            });
        });
        Label overviewSizeLabel = new Label();
        overviewSizeLabel.setPrefWidth(50);
        overviewSizeLabel.setMinWidth(50);
        overviewSizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(csvTreeObjectJFXTreeTableView.getCurrentItemsCount()), csvTreeObjectJFXTreeTableView.currentItemsCountProperty()));
        overviewUpperTabHBox.getChildren().addAll(overviewTextField, overviewSizeLabel);
        requestDocumentProgressBar.setVisible(false);
        requestDocumentProgress.setVisible(false);
        overviewLowerTabHBox.getChildren().addAll(requestDocumentProgressBar, requestDocumentProgress);
        overviewTabVBox.getChildren().addAll(csvTreeObjectJFXTreeTableView, overviewUpperTabHBox, overviewLowerTabHBox);
        overviewTab.setContent(overviewTabVBox);


        Tab listTab = new Tab();
        listTab.setText("List");
        VBox listTabVBox = new VBox();
        listTabVBox.setSpacing(10);
        HBox listUpperTabHBox = new HBox();
        HBox listLowerTabHBox = new HBox();
        JFXTextField listTextField = new JFXTextField();
        listTextField.setPrefWidth(500);
        listTextField.setMinWidth(500);
        listTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            linkTreeObjectJFXTreeTableView.setPredicate(props -> {
                final LinkTreeObject linkTreeObject = props.getValue();
                return linkTreeObject.name.get().contains(newValue);
            });
        });
        Label listSizeLabel = new Label();
        listSizeLabel.setPrefWidth(50);
        listSizeLabel.setMinWidth(50);
        listSizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(linkTreeObjectJFXTreeTableView.getCurrentItemsCount()), linkTreeObjectJFXTreeTableView.currentItemsCountProperty()));
        listLowerTabHBox.getChildren().addAll(listTextField, listSizeLabel);
        requestListProgressBar.setVisible(false);
        requestListProgress.setVisible(false);
        listUpperTabHBox.getChildren().addAll(requestListProgressBar, requestListProgress);
        listTabVBox.getChildren().addAll(linkTreeObjectJFXTreeTableView, listLowerTabHBox, listUpperTabHBox);
        listTab.setContent(listTabVBox);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            MenuBox.setCurrentTab(newValue.getText());
        });

        tabPane.setTabMinHeight(50);
        tabPane.setTabMaxHeight(50);
        tabPane.getTabs().addAll(overviewTab, listTab,
                fileTab("xml", xmlFileTreeObjectJFXTreeTableView, convertProgressBar, convertProgress),
                fileTab("txt", txtFileTreeObjectJFXTreeTableView, heideltimeProgressBar, heideltimeProgress),
                fileTab("ht", htFileTreeObjectJFXTreeTableView, csvProgressBar, csvProgress),
                fileTab("csv", csvFileTreeObjectJFXTreeTableView, null, null));
        this.setPrefSize(600, 550);
        this.getChildren().addAll(tabPane);
    }

    private Tab fileTab(String title, JFXTreeTableView<FileTreeObject> jfxTreeTableView, JFXProgressBar progressBar, Label progressLabel) {
        Tab tab = new Tab();
        tab.setText("files/" + title);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        HBox hBox = new HBox();

        Label sizeLabel = new Label();
        sizeLabel.setPrefWidth(50);
        sizeLabel.setMinWidth(50);
        sizeLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(jfxTreeTableView.getCurrentItemsCount()), jfxTreeTableView.currentItemsCountProperty()));

        JFXTextField jfxTextField = new JFXTextField();
        jfxTextField.setPrefWidth(500);
        jfxTextField.setMinWidth(500);
        jfxTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            jfxTreeTableView.setPredicate(props -> {
                final FileTreeObject fileTreeObject = props.getValue();
                return fileTreeObject.file.getName().contains(newValue)
                        || fileTreeObject.name.get().contains(newValue);
            });
        });

        hBox.getChildren().addAll(jfxTextField, sizeLabel);
        vBox.getChildren().addAll(jfxTreeTableView, hBox);
        if(progressBar != null) {
            HBox progressBox = new HBox();
            progressBar.setPrefWidth(500);
            progressBar.setProgress(0);
            progressBar.setVisible(false);
            progressLabel.setVisible(false);
            progressBox.getChildren().addAll(progressBar, progressLabel);
            vBox.getChildren().add(progressBox);
        }
        tab.setContent(vBox);
        return tab;
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
        linkTreeObjectJFXTreeTableView.setPrefHeight(400);
        linkTreeObjectJFXTreeTableView.getColumns().setAll(lineNumberColumn, nameColumn);

        csvTreeObjectJFXTreeTableView = new JFXTreeTableView<>(new RecursiveTreeItem<>(DataStore.overviewList, RecursiveTreeObject::getChildren));
        csvTreeObjectJFXTreeTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        csvTreeObjectJFXTreeTableView.setShowRoot(false);
        csvTreeObjectJFXTreeTableView.setPrefHeight(400);
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
        fileTreeObjectJFXTreeTableView.setPrefHeight(400);

        fileTreeObjectJFXTreeTableView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if(fileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItem() != null) DetailBox.updateFileDetails(fileTreeObjectJFXTreeTableView.getSelectionModel().getSelectedItem().getValue().file);
        });

        fileTreeObjectJFXTreeTableView.getColumns().setAll(nameColumn, sizeColumn);
        return fileTreeObjectJFXTreeTableView;
    }

    public static void updateProgressBar(String progressbarType, int current, int max) {
        switch (progressbarType) {
            case "requestDocument":
                Platform.runLater(() -> {
                    requestDocumentProgressBar.setProgress((double)current / (double)max);
                    requestDocumentProgress.setText(current + "/" + max);
                });
                break;
            case "requestList":
                Platform.runLater(() -> {
                    requestListProgressBar.setProgress((double)current / (double)max);
                    requestListProgress.setText(current + "/" + max);
                });
                break;
            case "convert":
                Platform.runLater(() -> {
                    convertProgressBar.setProgress((double)current / (double)max);
                    convertProgress.setText(current + "/" + max);
                });
                break;
            case "heideltime":
                Platform.runLater(() -> {
                    heideltimeProgressBar.setProgress((double)current / (double)max);
                    heideltimeProgress.setText(current + "/" + max);
                });
                break;
            case "csv":
                Platform.runLater(() -> {
                    csvProgressBar.setProgress((double)current / (double)max);
                    csvProgress.setText(current + "/" + max);
                });
                break;
        }
    }

    public static void setProgressBar(String progressbarType, boolean visible) {
        switch (progressbarType) {
            case "requestDocument":
                MenuBox.requestDocumentButton.setDisable(visible);
                requestDocumentProgressBar.setVisible(visible);
                requestDocumentProgress.setVisible(visible);
                break;
            case "requestList":
                MenuBox.requestListButton.setDisable(visible);
                requestListProgressBar.setVisible(visible);
                requestListProgress.setVisible(visible);
                break;
            case "convert":
                MenuBox.convertButton.setDisable(visible);
                convertProgressBar.setVisible(visible);
                convertProgress.setVisible(visible);
                break;
            case "heideltime":
                MenuBox.heideltimeButton.setDisable(visible);
                heideltimeProgressBar.setVisible(visible);
                heideltimeProgress.setVisible(visible);
                break;
            case "csv":
                MenuBox.csvButton.setDisable(visible);
                csvProgressBar.setVisible(visible);
                csvProgress.setVisible(visible);
                break;
        }
    }
}
