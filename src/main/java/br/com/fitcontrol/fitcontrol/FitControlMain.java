package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.Config.Config;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherSerial;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;
import javafx.application.Application;
import javafx.stage.Stage;


public class FitControlMain extends Application {

    NavigationSingleton navigation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.getInstance().setDatabase(main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio.MYSQL);
        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);

        EventManager evManager = new EventManager();

        /**
         *  Publishers
         */
        //publisher serial
        PublisherSerial publisherSerial = new PublisherSerial(evManager);

        //publisher tela
        PublisherTela publisherTela = PublisherTela.getInstance();
        publisherTela.registerEventManager(evManager);

        //Serial Communication
        SerialCommunicatorSingleton ser = SerialCommunicatorSingleton.getInstance();

        //register publisher
        ser.registerPublisher(publisherSerial);

        //start Serial Thread
        ser.start();


        SucessPopUpSingleton SucessPopUp = SucessPopUpSingleton.getInstance();
        SucessPopUp.registerStage(primaryStage);


        ErrorPopUpSingleton ErrorPopUp = ErrorPopUpSingleton.getInstance();
        ErrorPopUp.registerStage(primaryStage);


        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);

        navigation.navigate(NavigationSingleton.LOGIN_SCREEN);
        primaryStage.show();

    }

}
