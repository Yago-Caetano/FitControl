package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentsScreenController implements Initializable {

    @FXML
    public TableView<PagamentoModel> tabela;
    @FXML
    public TableColumn<PagamentoModel, Integer> id;
    @FXML
    public  TableColumn<PagamentoModel, String> data;
    @FXML
    public  TableColumn<PagamentoModel, Double> valor;

    public void initialize(URL location, ResourceBundle resources){
        id.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, Integer>("id"));
        data.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, String>("data"));
        valor.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, Double>("valor"));


        ObservableList<PagamentoModel> list = FXCollections.observableArrayList(new PagamentoModel(1 ,"09/07/2004",19.85),
                new PagamentoModel(1 ,"09/07/2004",19.85),new PagamentoModel(1 ,"09/07/2004",19.85),new PagamentoModel(1 ,"09/07/2004",19.85));

        tabela.setItems(list);

    }
}

