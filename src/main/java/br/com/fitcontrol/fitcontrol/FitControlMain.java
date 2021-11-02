package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Config.Config;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherSerial;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class FitControlMain extends Application {

    NavigationSingleton navigation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.getInstance().setDatabase(main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);


        Repositorio repositorio = FabricaRepositorio.Fabrica();
        var user  =repositorio.GetAll(EnumEntidadesDisponiveis.USUARIO);


        EventManager evManager = new EventManager();

        /**
         *  Publishers
         */
        //publisher serial
        PublisherSerial publisherSerial = new PublisherSerial(evManager);

        //publisher tela
        PublisherTela publisherTela = new PublisherTela(evManager);

        //Serial Communication
        SerialCommunicatorSingleton ser = SerialCommunicatorSingleton.getInstance();

        //register publisher
        ser.registerPublisher(publisherSerial);

        //start Serial Thread
        ser.start();



        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);

        navigation.navigate(NavigationSingleton.LOGIN_SCREEN, new iNavCallback() {
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
