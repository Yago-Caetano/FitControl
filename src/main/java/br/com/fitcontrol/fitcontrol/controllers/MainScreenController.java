package br.com.fitcontrol.fitcontrol.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private HBox catracaHbox;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        catracaHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,catracaClicked);
    }

    //Creating the mouse event handler
    EventHandler<MouseEvent> catracaClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            System.out.println("Clicou na catraca");
        }
    };

}
