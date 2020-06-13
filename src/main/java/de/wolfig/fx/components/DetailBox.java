package de.wolfig.fx.components;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DetailBox extends VBox {

    static JFXTextArea textArea = new JFXTextArea();
    static Label titleLabel = new Label("&Title");
    static Label urnLabel = new Label("&URN");
    static Label dateLabel = new Label("&Date");
    static Label wordCountLabel = new Label("&WordLength");

    public DetailBox() {
        HBox infoBox = new HBox();
        infoBox.setPrefSize(600, 50);
        //infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox leftBox = new VBox(titleLabel, urnLabel);
        leftBox.setPrefWidth(500);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        VBox rightBox = new VBox(dateLabel, wordCountLabel);
        rightBox.setAlignment(Pos.CENTER_LEFT);

        infoBox.getChildren().addAll(leftBox, rightBox);

        VBox fileBox = new VBox();
        fileBox.setPrefSize(600, 500);
        //fileBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 0), CornerRadii.EMPTY, Insets.EMPTY)));

        textArea.setStyle("-fx-font-size: 10px; -fx-font-family: 'Lucida Console'; -fx-font-style: normal");
        textArea.setWrapText(false);
        textArea.setPrefHeight(550);
        textArea.setEditable(false);
        textArea.setText("No file selected");

        fileBox.getChildren().addAll(textArea);

        this.getChildren().addAll(infoBox, fileBox);
    }

    public static void updateFileDetails(File file) {
        try {
            if(file.getPath().startsWith(".\\files\\xml\\")) {
                titleLabel.setText("$Title:");
                urnLabel.setText("ID:");
                dateLabel.setText("$Date:");
                wordCountLabel.setText("$WordLength:");
                textArea.setText(String.valueOf(Files.readAllLines(file.toPath())));
            } else if(file.getPath().startsWith(".\\files\\txt\\")) {
                titleLabel.setText(Files.readAllLines(file.toPath()).get(0));
                urnLabel.setText(Files.readAllLines(file.toPath()).get(3).replace("urn:contentItem:", ""));
                dateLabel.setText(Files.readAllLines(file.toPath()).get(2));
                wordCountLabel.setText(Files.readAllLines(file.toPath()).get(1));
                textArea.setText(String.valueOf(Files.readAllLines(file.toPath())));
            } else if(file.getPath().startsWith(".\\files\\ht\\")) {
                titleLabel.setText(Files.readAllLines(file.toPath()).get(3));
                urnLabel.setText(Files.readAllLines(file.toPath()).get(6).replace("urn:contentItem:", ""));
                dateLabel.setText(Files.readAllLines(file.toPath()).get(5));
                wordCountLabel.setText(Files.readAllLines(file.toPath()).get(4));
                textArea.setText(String.valueOf(Files.readAllLines(file.toPath())));
            } else if(file.getPath().startsWith(".\\files\\csv\\")) {
                textArea.setText(String.valueOf(Files.readAllLines(file.toPath())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
