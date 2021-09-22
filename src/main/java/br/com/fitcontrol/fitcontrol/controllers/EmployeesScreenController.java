package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.classes.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesScreenController implements Initializable {

    @FXML
    public TableView<Funcionario> tabela;
    @FXML
    public TableColumn<Funcionario, Integer> id;
    @FXML
    public  TableColumn<Funcionario, String> nome;
    @FXML
    public TableColumn<Funcionario, Integer> nivel;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        id.setCellValueFactory(
                new PropertyValueFactory<Funcionario, Integer>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<Funcionario, String>("nome"));
        nivel.setCellValueFactory(
                new PropertyValueFactory<Funcionario, Integer>("nivel"));


        ObservableList<Funcionario> list = FXCollections.observableArrayList(new Funcionario(1 ,"Jeferson",1),
                new Funcionario(1 ,"Jeferson",2),new Funcionario(1 ,"Jeferson",3),new Funcionario(5 ,"Jeferson",1));

        tabela.setItems(list);

    }

}
