package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import classes.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsScreenController implements Initializable {


    @FXML
    public TableView<Cliente> tabela;
    @FXML
    public TableColumn<Cliente, Integer> idCol;
    @FXML
    public  TableColumn<Cliente, String> nomeCol;
    @FXML
    public TableColumn<Cliente, String> emailCol;
    @FXML
    public TableColumn<Cliente, String> telefoneCol;
    @FXML
    public TableColumn<Cliente, Integer> pontoCol;


    private List<Cliente> clientes = new ArrayList<Cliente>();



    @Override
    public void initialize(URL location, ResourceBundle resources){

        carregarClientes();
    }

   /* private ObservableList<Cliente> listaDeClientes() {
        return FXCollections.observableArrayList(
                new Cliente(1,"Douglas","Douglas@gmail.com","4992-8922", 20),
                new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30),
                new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30),
                new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30),
                new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30),
                new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30)
        );
    }*/

    public void carregarClientes(){

        Cliente c1 = new Cliente(1,"Douglas","Douglas@gmail.com","4992-8922", 20);
        Cliente c2 = new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30);
        Cliente c3 = new Cliente(2,"Rafael","Rafael@gmail.com","3471-1195", 30);

        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);

        ObservableList<Cliente> obsClientes = FXCollections.observableArrayList(clientes);

        idCol.setCellValueFactory(
                new PropertyValueFactory<Cliente, Integer>("id"));
        nomeCol.setCellValueFactory(
                new PropertyValueFactory<Cliente, String>("nome"));
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Cliente, String>("email"));
        telefoneCol.setCellValueFactory(
                new PropertyValueFactory<Cliente, String>("telefone"));
        pontoCol.setCellValueFactory(
                new PropertyValueFactory<Cliente, Integer>("pontos"));

        tabela.setItems(obsClientes);
    }

}
