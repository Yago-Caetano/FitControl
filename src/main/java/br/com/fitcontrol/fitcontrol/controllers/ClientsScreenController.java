package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsScreenController implements Initializable {


    @FXML
    public TableView<ClienteModel> tabela;
    @FXML
    public TableColumn<ClienteModel, Integer> id;
    @FXML
    public  TableColumn<ClienteModel, String> nome;
    @FXML
    public TableColumn<ClienteModel, String> email;
    @FXML
    public TableColumn<ClienteModel, String> telefone;
    @FXML
    public TableColumn<ClienteModel, Integer> ponto;


    private List<ClienteModel> clienteModels = new ArrayList<ClienteModel>();

    private NavigationSingleton navigation;
    @FXML
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        executeNavigation(NavigationSingleton.MAIN_SCREEN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("nome"));
        email.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("email"));
        telefone.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("telefone"));
        ponto.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("pontos"));

        ObservableList<ClienteModel> list = FXCollections.observableArrayList( new ClienteModel(1,"Douglas","Douglas@gmail.com","4992-8922", 20),
                new ClienteModel(2,"Rafael","Rafael@gmail.com","3471-1195", 30),new ClienteModel(3,"Julio","Julio@gmail.com","2781-9895", 70));

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
