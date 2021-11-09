package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportScreenController implements Initializable {

    private NavigationSingleton navigation;
    @FXML
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        try {
                navigation.goBack(new iNavCallback() {
                    @Override
                    public void navigateCb(String screenName) throws Exception {
                        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                        navigation.getStage().setScene(scene);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources){
        navigation = NavigationSingleton.getInstance();
    }


}
