package de.wolfig.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void start(Stage stage) {

        // menu
        HBox menuBox = new HBox();
        menuBox.setPrefSize(1200, 50);
        menuBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        menuBox.getChildren().addAll();


        // main
        VBox listBox = new VBox();
        listBox.setPrefSize(600, 550);
        listBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        listBox.getChildren().addAll();

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

    public static void main(String... args) {
        launch(args);
    }
}
