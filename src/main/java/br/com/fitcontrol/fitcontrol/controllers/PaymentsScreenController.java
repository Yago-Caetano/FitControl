package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import classes.Cliente;
import classes.Pagamento;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentsScreenController implements Initializable {

    @FXML
    public TableView<Pagamento> tabela;
    @FXML
    public TableColumn<Pagamento, Integer> idCol;
    @FXML
    public  TableColumn<Pagamento, String> dataCol;
    @FXML
    public TableColumn<Pagamento, Double> valorCol;

    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        carregarPagamentos();
    }

    public void carregarPagamentos(){

        Pagamento p1 = new Pagamento(1,"20/10/2021",200.50);
        Pagamento p2 = new Pagamento(2,"17/10/2021",200.50);
        Pagamento p3 = new Pagamento(3,"12/10/2021",200.50);

        pagamentos.add(p1);
        pagamentos.add(p2);
        pagamentos.add(p3);

        ObservableList<Pagamento> obsPagamentos = FXCollections.observableArrayList(pagamentos);

        idCol.setCellValueFactory(
                new PropertyValueFactory<Pagamento, Integer>("id"));
        dataCol.setCellValueFactory(
                new PropertyValueFactory<Pagamento, String>("data"));
        valorCol.setCellValueFactory(
                new PropertyValueFactory<Pagamento, Double>("valor"));


        tabela.setItems(obsPagamentos);
    }
}

