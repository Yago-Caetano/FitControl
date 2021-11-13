package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.ParametroFiltroDAO;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentsScreenController extends PadrãoController implements Initializable {

    @FXML
    public TableView<PagamentoModel> tabela;
    @FXML
    public TableColumn<PagamentoModel, Integer> id;
    @FXML
    public  TableColumn<PagamentoModel, String> data;
    @FXML
    public  TableColumn<PagamentoModel, Double> valor;

    @FXML
    public  TableColumn<PagamentoModel, Void> acao;

    @FXML
    public DatePicker dtStart,dtEnd;

    @FXML
    public TextField txtValor;

    private NavigationSingleton navigation;



    @FXML
    protected void voltarClicked(){
        try {
            navigation.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void novoPagamentoClicked() {
        executeNavigation(NavigationSingleton.PAYMENT_EDIT_SCREEN);
    }
    PagamentosMySQLDAO dao = new PagamentosMySQLDAO();

    public void initialize(URL location, ResourceBundle resources){

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, Integer>("id"));
        data.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, String>("data"));
        valor.setCellValueFactory(
                new PropertyValueFactory<PagamentoModel, Double>("valor"));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        ObservableList<PagamentoModel> list = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        carregarDados();
        colunaAcoes();
    }




    @FXML
    protected void filterClicked(){

        List<ParametroFiltroDAO> params = new ArrayList<>();

        if(dtStart.getValue() != null)
        {
            String data = dtStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ParametroFiltroDAO mParam = new ParametroFiltroDAO("_Data",data,">=");
            params.add(mParam);
        }

        if(dtEnd.getValue() != null)
        {
            String data = dtEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ParametroFiltroDAO mParam = new ParametroFiltroDAO("_Data",data,"<=");
            params.add(mParam);

        }

        if(txtValor.getText().length()>0)
        {

            ParametroFiltroDAO mParam = new ParametroFiltroDAO("Valor",txtValor.getText(),"=");
            params.add(mParam);
        }

        try {
                ArrayList<PagamentoModel> filtragem = dao.filtro(params);

                tabela.setItems(FXCollections.observableArrayList(filtragem));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void carregarDados() {

        ObservableList<PagamentoModel> lista = null;
        try {
            lista = FXCollections.observableArrayList(dao.lista());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tabela.setItems(lista);
    }

    private void colunaAcoes(){

        Callback<TableColumn<PagamentoModel, Void>, TableCell<PagamentoModel, Void>> cellFactory = new Callback<TableColumn<PagamentoModel, Void>, TableCell<PagamentoModel, Void>>() {
            @Override
            public TableCell<PagamentoModel, Void> call(final TableColumn<PagamentoModel, Void> param) {
                final TableCell<PagamentoModel, Void> cell = new TableCell<PagamentoModel, Void>() {

                    private final Button btnDeletar = new Button("Deletar");

                    {
                        btnDeletar.setStyle("-fx-background-color:#e05f55;");
                        btnDeletar.setOnAction((ActionEvent event) -> {
                            PagamentoModel pagamento = getTableView().getItems().get(getIndex());

                            PublisherTela p = PublisherTela.getInstance();
                            try {
                                p.DeleteUser(pagamento);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                carregarDados();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                    }

                    private final Button btnEditar = new Button("Editar");

                    {
                        btnEditar.setStyle("-fx-background-color:#2bc5d6;");

                        btnEditar.setOnAction((ActionEvent event) -> {
                            PagamentoModel pagamento = getTableView().getItems().get(getIndex());

                            navigation.navigate(NavigationSingleton.PAYMENT_EDIT_SCREEN,pagamento);

                        });

                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(btnDeletar, btnEditar);
                            pane.setStyle("-fx-alignment:center");
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        acao.setCellFactory(cellFactory);

        tabela.getColumns().add(acao);
    }

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId);
    }

    @Override
    protected void PreviousScreenDataReceived() {

    }
}

