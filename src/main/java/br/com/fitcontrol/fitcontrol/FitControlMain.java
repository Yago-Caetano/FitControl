package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Config.Config;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherSerial;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        //publisher serial
        PublisherSerial publisherSerial = new PublisherSerial(evManager);

        SerialCommunicatorSingleton ser = SerialCommunicatorSingleton.getInstance();

        //register publisher
        ser.registerPublisher(publisherSerial);

        //teste para abertura de porta serial
        for(SerialPort s : ser.getAvailablePorts())
        {
            System.out.println(s.getSystemPortName() + " - " + s.getDescriptivePortName());
        }
        ser.start();
        ser.connect(ser.getAvailablePorts()[2]);

        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);


        /*Acesso acesso = new Acesso();
        UsuarioModel usuarioModel= new UsuarioModel();
        usuarioModel.setLogin("R");
        usuarioModel.setSenha("123");
        boolean UsuarioOk = acesso.validaUsuario(usuarioModel);
        System.out.println("Usuario ok " + UsuarioOk);*/

        /*//FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("main-screen.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource("rewards-screen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
        primaryStage.setTitle("Fit Control");
        primaryStage.setScene(scene);
        primaryStage.show();*/
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
