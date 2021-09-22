package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FitControlMain extends Application {

    NavigationSingleton navigation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);
        /*//FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("main-screen.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("rewards-screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        primaryStage.setTitle("Fit Control");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        navigation.navigate(NavigationSingleton.MAIN_SCREEN, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                primaryStage.setTitle("Fit Control");
                primaryStage.setScene(scene);

            }
        });
        primaryStage.show();
    }

}
