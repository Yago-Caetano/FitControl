package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.Acessologin;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Acesso.AcessoMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {

    private NavigationSingleton navigation;
    @FXML
    public TextField txtLogin;
    @FXML
    public TextField txtSenha;
    @FXML
    protected void entrarClicked() throws Exception {
       /* FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
        ArrayList<FuncionarioModel> lista = dao.lista();
        Acessologin validar = new Acessologin();

        for (FuncionarioModel f: lista
             ) {
            if(f.getLogin() == txtLogin.getText()){

                if(validar.validaUsuario(f))
                    executeNavigation(NavigationSingleton.MAIN_SCREEN);
                else{
                    txtSenha.setText("");
                    txtLogin.setText("");
                    txtSenha.setPromptText("Senha Inválida");
                }

            }
        }
        txtSenha.setText("");
        txtLogin.setText("");
        txtLogin.setPromptText("Usuário inválido");*/

        executeNavigation(NavigationSingleton.MAIN_SCREEN);

    }
    @FXML
    protected void cadastrarClicked() {
        executeNavigation(NavigationSingleton.EMPLOYEE_EDIT_SCREEN);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                navigation.getStage().setScene(scene);
            }
        });
    }
}
