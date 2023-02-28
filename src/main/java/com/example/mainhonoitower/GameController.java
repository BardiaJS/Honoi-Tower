package com.example.mainhonoitower;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameController {


    // starting initialization
    @FXML
    public void initialize() {
        createDisks(3);
    }

    //initialization rods
    public VBox rod1;
    public VBox rod2;
    public VBox rod3;
    public TextField disksCountField;

    // number of disks will be increased
    public void btnIncreaseClicked(ActionEvent event) {
        int count = Integer.parseInt(disksCountField.getText());
        if (count < 8) {
            count++;
        }

        disksCountField.setText(String.valueOf(count));
        createDisks(count);
    }

    // number of disks will  be decreased
    public void btnDecreraseClicked(ActionEvent event) {
        int count = Integer.parseInt(disksCountField.getText());
        if (count > 2) {
            count--;
        }

        disksCountField.setText(String.valueOf(count));
        createDisks(count);
    }


    // number of disks and rods and set their width and height
    public void createDisks(int count) {
        rod1.getChildren().remove(0, rod1.getChildren().size());
        rod2.getChildren().remove(0, rod2.getChildren().size());
        rod3.getChildren().remove(0, rod3.getChildren().size());
        for (int i = 1; i <= count; i++) {
            Button currentButton = new Button(String.valueOf(i));
            currentButton.setPrefWidth(20 + 15*i);
            currentButton.setPrefHeight(20);
            rod1.getChildren().add(currentButton);
        }

    }

    // main method and doing job
    public void honoiMethod(int n, VBox from_rod, VBox to_rod, VBox helper_rod)
    {
        if (n == 1)
        {
            System.out.println("Take disk 1 from rod " +  from_rod + " to rod " + to_rod);
            Button buttonSelected = (Button) from_rod.getChildren().get(0);
            TranslateTransition animation = new TranslateTransition();
            animation.setNode(buttonSelected);
            animation.setDuration(Duration.seconds(2));
            animation.setByX(300);
            animation.play();
            animation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    buttonSelected.setTranslateX(0);
                    to_rod.getChildren().add(0,buttonSelected);
                }
            });
            return;
        }
        honoiMethod(n-1, from_rod, helper_rod, to_rod);
        System.out.println("Take disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
        Button buttonSelected = (Button) from_rod.getChildren().get(0);
        TranslateTransition animation = new TranslateTransition();
        animation.setNode(buttonSelected);
        animation.setDuration(Duration.seconds(2));
        animation.setByX(300);
        animation.play();
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonSelected.setTranslateX(0);
                to_rod.getChildren().add(0,buttonSelected);
            }
        });
        honoiMethod(n-1, helper_rod, to_rod, from_rod);
    }

    // starting animation method
    public void startAnimation(ActionEvent event) {
        int numberOfdisks = Integer.parseInt(disksCountField.getText());
        honoiMethod(numberOfdisks,rod1 , rod3 , rod2);
    }
}