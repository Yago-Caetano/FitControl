package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CatracaEditScreenController implements Initializable {
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtNome;
    @FXML
    public TextField txtModelo;

    private NavigationSingleton navigation;
    private boolean update;
    @FXML
    private Button voltar;
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() throws SQLException {
        CatracaModel catraca = new CatracaModel();
        CatracaMySQLDAO dao = new CatracaMySQLDAO();

        catraca.setId(txtID.getText());
        catraca.setNome(txtNome.getText());
        catraca.setModelo(txtModelo.getText());


        PublisherTela p = PublisherTela.getInstance();

        //Verifica se é Edit ou Insert
        if(!update){                        //Insert
            p.RegisterCatraca(catraca);
        }
        else{                            // Edit
            catraca = (CatracaModel) (dao.localiza(catraca.getId()));
            catraca.setId(txtID.getText());
            catraca.setNome(txtNome.getText());
            catraca.setModelo(txtModelo.getText());
            p.UpdateCatraca(catraca);
            setUpdate(false);
        }
        voltarClicked();

    }



    /**
     * Preenche os TextFields da tela com a catraca que deseja editar.
     */
    void preencheTextField(CatracaModel catraca) {

        txtID.setText(catraca.getId());
        txtNome.setText(catraca.getNome());
        txtModelo.setText(catraca.getModelo());

        txtID.setEditable(false);
        txtID.setDisable(true);
    }

    void setUpdate(boolean key) {
        this.update = key;
    }

}


