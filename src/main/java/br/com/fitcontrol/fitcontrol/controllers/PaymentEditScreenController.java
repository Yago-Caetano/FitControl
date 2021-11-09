package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;

public class PaymentEditScreenController implements Initializable {


    @FXML
    public TextField txtID;

    @FXML
    public DateFormat txtData;
    @FXML
    public TextField txtValor;

    @FXML
    public TableView<PagamentoModel> tabela;
    @FXML
    public TableColumn<PagamentoModel, Integer> id;
    @FXML
    public  TableColumn<PagamentoModel, String> data;
    @FXML
    public  TableColumn<PagamentoModel, Double> valor;

    private boolean update;
    private NavigationSingleton navigation;
    @FXML
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    void preencheTextField(PagamentoModel pagamento) {

        txtID.setText((pagamento.getId()));
        txtData.format(pagamento.getData());
        txtID.setText(Integer.toString(pagamento.getIdCliente()));
        txtValor.setText(Double.toString(pagamento.getValor()));

        txtID.setEditable(false);
        txtID.setDisable(true);
    }


}
