package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.Acessologin;
import br.com.fitcontrol.fitcontrol.Acesso.FuncionarioLogado;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Acesso.AcessoMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.error.ErrorPopUp;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreenController implements Initializable {

    private NavigationSingleton navigation;
    @FXML
    public TextField txtLogin;
    @FXML
    public PasswordField txtSenha;
    @FXML
    protected void entrarClicked() throws Exception {
        FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
        ArrayList<FuncionarioModel> lista = dao.lista();

        Acessologin validar = new Acessologin();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        for (FuncionarioModel f: lista
             ) {
            if(f.getLogin().equals(login)){
                f.setSenha(senha);
                if(validar.validaUsuario(f)){

                    FuncionarioLogado.setNome(f.getNome());
                    executeNavigation(NavigationSingleton.MAIN_SCREEN);

                }
                else{
                    clearFields();
                    navigation.showErrorMessage("Senha Inválida");

                }
            }else {
                clearFields();
                navigation.showErrorMessage("Login não encontrado");

            }
        }

    }

    private void clearFields()
    {
        txtSenha.setText("");
        txtLogin.setText("");
    }


    @FXML
    protected void cadastrarClicked() {
        executeNavigation(NavigationSingleton.CLIENTS_EDIT_SCREEN);
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
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                navigation.getStage().setScene(scene);
            }
        });
    }
}
