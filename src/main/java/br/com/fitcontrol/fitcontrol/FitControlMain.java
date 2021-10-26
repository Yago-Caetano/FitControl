package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Config.Config;
import br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
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
        Config.getInstance().setDatabase(main.java.br.com.fitcontrol.fitcontrol.Enums.TipoRepositorio.MYSQL);
        navigation = NavigationSingleton.getInstance();
        navigation.setStage(primaryStage);


        Repositorio repositorio = FabricaRepositorio.Fabrica();
        var user  =repositorio.GetAll(EntidadesDisponiveis.USUARIO);

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
