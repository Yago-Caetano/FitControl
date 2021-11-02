package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentsScreenController implements Initializable {

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

    private NavigationSingleton navigation;
    @FXML
    private Button voltar,novoPagamento;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
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
            /*list = FXCollections.observableArrayList(new PagamentoModel(1 ,(java.sql.Date)format.parse("09/07/2004"),19.85),
                    new PagamentoModel(2 ,(java.sql.Date)format.parse("03/08/2005"),20.78),
                    new PagamentoModel(3 ,(java.sql.Date)format.parse("30/06/2006"),7.81),
                    new PagamentoModel(4 ,(java.sql.Date)format.parse("27/09/2021"),210.62));*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        tabela.setItems(list);

        //colunaAcoes();
    }

    public void carregarDados() throws SQLException {

        ObservableList<PagamentoModel> listaTeste = FXCollections.observableArrayList(dao.lista());

        tabela.setItems(listaTeste);
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
                            EventManager evtmanager = new EventManager();

                            PublisherTela p = new PublisherTela(evtmanager);
                            try {
                                p.DeleteUser(pagamento);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            try {
                                carregarDados();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        });

                    }

                    private final Button btnEditar = new Button("Editar");

                    {
                        btnEditar.setStyle("-fx-background-color:#2bc5d6;");

                        btnEditar.setOnAction((ActionEvent event) -> {
                            PagamentoModel pagamento = getTableView().getItems().get(getIndex());

                            FXMLLoader loader = new FXMLLoader(FitControlMain.class.getResource("client-edit-screen.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientsScreenController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            PaymentEditScreenController paymentEditController = loader.getController();

                            paymentEditController.setUpdate(true);
                            paymentEditController.preencheTextField(pagamento);
                            Parent parent = loader.getRoot();
                            navigation.getStage().setScene(new Scene(parent));

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

