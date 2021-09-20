package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PaymentsScreenController extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("payments-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        primaryStage.setTitle("Fit Control");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
