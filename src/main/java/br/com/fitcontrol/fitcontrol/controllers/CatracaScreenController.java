package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
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

public class CatracaScreenController implements Initializable {


    @FXML
    public TableView<CatracaModel> tabela;
    @FXML
    public TableColumn<CatracaModel, Integer> id;
    @FXML
    public  TableColumn<CatracaModel, String> modelo;

    private NavigationSingleton navigation;
    @FXML
    private Button voltar,novoCliente;

    @FXML
    protected void voltarClicked() {
        executeNavigation(NavigationSingleton.MAIN_SCREEN);
    }
    @FXML
    protected void novaCatracaClicked() {
        executeNavigation(NavigationSingleton.CATRACA_EDIT_SCREEN);
    }

    public void initialize(URL location, ResourceBundle resources){
        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<CatracaModel, Integer>("id"));
        modelo.setCellValueFactory(
                new PropertyValueFactory<CatracaModel, String>("modelo"));

        ObservableList<CatracaModel> list = FXCollections.observableArrayList( new CatracaModel(1,"Catraca 1"),
                new CatracaModel(2,"Catraca 2"),new CatracaModel(3,"Catraca 3"));

        tabela.setItems(list);
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
}
