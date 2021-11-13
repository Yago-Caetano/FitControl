package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.Acessologin;
import br.com.fitcontrol.fitcontrol.Acesso.FuncionarioLogado;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
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

        Acessologin login = new Acessologin();

        FuncionarioModel UserModel = new FuncionarioModel();
        UserModel.setLogin(txtLogin.getText());
        UserModel.setSenha(txtSenha.getText());

        if(login.validaUsuario(UserModel))
        {
            executeNavigation(NavigationSingleton.MAIN_SCREEN);
        }
        else
        {
            clearFields();
            ErrorPopUpSingleton.getInstance().showError("Login não encontrado");
        }

    }

    private void clearFields()
    {
        txtSenha.setText("");
        txtLogin.setText("");
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
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                navigation.getStage().setScene(scene);
            }
        });
    }
}
