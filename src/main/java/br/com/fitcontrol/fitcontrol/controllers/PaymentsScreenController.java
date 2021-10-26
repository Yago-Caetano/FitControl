package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private NavigationSingleton navigation;
    @FXML
    private Button voltar,novoPagamento;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws IOException {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void novoPagamentoClicked() {
        executeNavigation(NavigationSingleton.PAYMENT_EDIT_SCREEN);
    }

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
            list = FXCollections.observableArrayList(new PagamentoModel(1 ,(java.sql.Date)format.parse("09/07/2004"),19.85),
                    new PagamentoModel(2 ,(java.sql.Date)format.parse("03/08/2005"),20.78),
                    new PagamentoModel(3 ,(java.sql.Date)format.parse("30/06/2006"),7.81),
                    new PagamentoModel(4 ,(java.sql.Date)format.parse("27/09/2021"),210.62));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

