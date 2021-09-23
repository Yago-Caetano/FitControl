package br.com.fitcontrol.fitcontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FitControlClients extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("clients-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        primaryStage.setTitle("Fit Control");
        scene.getStylesheets().add("br/com/fitcontrol/fitcontrol/css/tabela.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
