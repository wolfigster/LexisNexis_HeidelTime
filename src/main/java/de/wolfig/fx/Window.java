package de.wolfig.fx;

import de.wolfig.Main;
import de.wolfig.fx.components.DetailBox;
import de.wolfig.fx.components.ListBox;
import de.wolfig.fx.components.MenuBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void start(Stage stage) {
        DataStore.loadDataStore();

        // main-main
        HBox mainBox = new HBox();
        mainBox.setPrefSize(1200, 550);
        mainBox.getChildren().addAll(new ListBox(), new DetailBox());

        // scene
        final Scene scene = new Scene(new StackPane(new FlowPane(new MenuBox(), mainBox)), 1200, 600);
        scene.getStylesheets().addAll(Main.class.getResource("/css/jfoenix-components.css").toExternalForm(),
                Main.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                Main.class.getResource("/css/jfoenix-fonts.css").toExternalForm());
        stage.setTitle("LexisNexis Heideltime");
        stage.setMinWidth(1220);
        stage.setMaxWidth(1220);
        stage.setMinHeight(640);
        stage.setMaxHeight(640);
        stage.setScene(scene);
        stage.show();
    }
}
