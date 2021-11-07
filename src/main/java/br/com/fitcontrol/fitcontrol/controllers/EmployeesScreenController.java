package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesScreenController implements Initializable {

    @FXML
    public TableView<FuncionarioModel> tabela;
    @FXML
    public TableColumn<FuncionarioModel, Integer> id;
    @FXML
    public  TableColumn<FuncionarioModel, String> nome;
    @FXML
    public TableColumn<FuncionarioModel, Integer> nivel;

    private NavigationSingleton navigation;
    @FXML
    private Button voltar,novoFuncionario;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws IOException {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void NovoFuncionarioClicked() {
        executeNavigation(NavigationSingleton.EMPLOYEE_EDIT_SCREEN);
    }

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                navigation.getStage().setScene(scene);
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, Integer>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, String>("nome"));
        nivel.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, Integer>("nivel"));


        ObservableList<FuncionarioModel> list = FXCollections.observableArrayList(new FuncionarioModel("1" ,"Jeferson",1),
                new FuncionarioModel("2" ,"Claudia",2),new FuncionarioModel("3" ,"Roberta",3),new FuncionarioModel("4" ,"Murilo",1));

        tabela.setItems(list);

    }


}
