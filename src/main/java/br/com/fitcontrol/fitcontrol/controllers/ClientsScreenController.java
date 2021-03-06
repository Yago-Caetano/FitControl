package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.ParametroFiltroDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientsScreenController extends  PadrãoController implements Initializable {

    @FXML
    public TableView<ClienteModel> tabela;
    @FXML
    public TableColumn<ClienteModel, String> id;
    @FXML
    public  TableColumn<ClienteModel, String> nome;
    @FXML
    public TableColumn<ClienteModel, String> email;
    @FXML
    public TableColumn<ClienteModel, String> telefone;
    @FXML
    public TableColumn<ClienteModel, Integer> ponto;
    @FXML
    public TableColumn<ClienteModel,Void> acao;

    @FXML
    public TextField txtNomeFiltro;

    private NavigationSingleton navigation;

    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void novoClienteClicked() {
        executeNavigation(NavigationSingleton.CLIENTS_EDIT_SCREEN);
    }

    ObservableList<ClienteModel>  list = FXCollections.observableArrayList();
    ClienteMySQLDAO dao = new ClienteMySQLDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("nome"));
        email.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("login"));
        telefone.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("telefone"));
        ponto.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("pontos"));

        try {
            carregarDados();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        colunaAcoes();

    }

    @FXML
    private void filterClicked(){
        List<ParametroFiltroDAO> params = new ArrayList<>();


        if(txtNomeFiltro.getText().length()>0)
        {

            ParametroFiltroDAO mParam = new ParametroFiltroDAO("Nome", "%" + txtNomeFiltro.getText() + "%"," LIKE ");
            params.add(mParam);
        }

        try {
            ArrayList<ClienteModel> filtragem = dao.filtro(params);

            tabela.setItems(FXCollections.observableArrayList(filtragem));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId);
    }

    public void carregarDados() throws Exception {

        ObservableList<ClienteModel> lista = FXCollections.observableArrayList(dao.lista());

       /* if(lista.isEmpty()){
            PublisherTela p = new PublisherTela();
            ClienteModel c = new ClienteModel();
            c.setNome("Cliente1");
            c.setLogin("cliente1");
            c.setTelefone("00009");
            c.setPontos(0);
            p.RegisterUser(c);
            carregarDados();
        }*/


        tabela.setItems(lista);
    }

    private void colunaAcoes(){

        Callback<TableColumn<ClienteModel, Void>, TableCell<ClienteModel, Void>> cellFactory = new Callback<TableColumn<ClienteModel, Void>, TableCell<ClienteModel, Void>>() {
            @Override
            public TableCell<ClienteModel, Void> call(final TableColumn<ClienteModel, Void> param) {
                final TableCell<ClienteModel, Void> cell = new TableCell<ClienteModel, Void>() {

                    private final Button btnDeletar = new Button("Deletar");
                    {
                        btnDeletar.setStyle("-fx-background-color:#e05f55;");
                        btnDeletar.setOnAction((ActionEvent event) -> {
                            ClienteModel cliente = getTableView().getItems().get(getIndex());

                            PublisherTela p =  PublisherTela.getInstance();
                            try {
                                p.DeleteUser(cliente);
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
                            ClienteModel aluno = getTableView().getItems().get(getIndex());

                            navigation.navigate(NavigationSingleton.CLIENTS_EDIT_SCREEN,aluno);

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


    @Override
    public Object getDataFromPreviousScreen() {
        return null;
    }

    @Override
    protected void PreviousScreenDataReceived() {

    }
}
