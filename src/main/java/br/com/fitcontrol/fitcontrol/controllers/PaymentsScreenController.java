package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.classes.Pagamento;
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
    public TableView<Pagamento> tabela;
    @FXML
    public TableColumn<Pagamento, Integer> id;
    @FXML
    public  TableColumn<Pagamento, String> data;
    @FXML
    public  TableColumn<Pagamento, Double> valor;

    public void initialize(URL location, ResourceBundle resources){
        id.setCellValueFactory(
                new PropertyValueFactory<Pagamento, Integer>("id"));
        data.setCellValueFactory(
                new PropertyValueFactory<Pagamento, String>("data"));
        valor.setCellValueFactory(
                new PropertyValueFactory<Pagamento, Double>("valor"));


        ObservableList<Pagamento> list = FXCollections.observableArrayList(new Pagamento(1 ,"09/07/2004",19.85),
                new Pagamento(1 ,"09/07/2004",19.85),new Pagamento(1 ,"09/07/2004",19.85),new Pagamento(1 ,"09/07/2004",19.85));

        tabela.setItems(list);

    }
}

