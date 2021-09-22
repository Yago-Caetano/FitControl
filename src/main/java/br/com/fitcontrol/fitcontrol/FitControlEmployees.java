package br.com.fitcontrol.fitcontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FitControlEmployees extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("employees-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        primaryStage.setTitle("Fit Control");
        scene.getStylesheets().add("CSS/tabela.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
