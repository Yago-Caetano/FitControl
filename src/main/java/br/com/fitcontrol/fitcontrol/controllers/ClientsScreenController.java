package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsScreenController implements Initializable {


    @FXML
    public TableView<ClienteModel> tabela;
    @FXML
    public TableColumn<ClienteModel, Integer> idCol;
    @FXML
    public  TableColumn<ClienteModel, String> nomeCol;
    @FXML
    public TableColumn<ClienteModel, String> emailCol;
    @FXML
    public TableColumn<ClienteModel, String> telefoneCol;
    @FXML
    public TableColumn<ClienteModel, Integer> pontoCol;


    private List<ClienteModel> clienteModels = new ArrayList<ClienteModel>();



    @Override
    public void initialize(URL location, ResourceBundle resources){

        carregarClientes();
    }

    public void carregarClientes(){

        ClienteModel c1 = new ClienteModel(1,"Douglas","Douglas@gmail.com","4992-8922", 20);
        ClienteModel c2 = new ClienteModel(2,"Rafael","Rafael@gmail.com","3471-1195", 30);
        ClienteModel c3 = new ClienteModel(3,"Julio","Julio@gmail.com","2781-9895", 70);

        clienteModels.add(c1);
        clienteModels.add(c2);
        clienteModels.add(c3);

        ObservableList<ClienteModel> obsClienteModels = FXCollections.observableArrayList(clienteModels);

        idCol.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("id"));
        nomeCol.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("nome"));
        emailCol.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("email"));
        telefoneCol.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("telefone"));
        pontoCol.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("pontos"));

        tabela.setItems(obsClienteModels);
    }

}
